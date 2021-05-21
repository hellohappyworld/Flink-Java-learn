package com.scallion.job;

import com.scallion.common.Common;
import com.scallion.transform.CountAggregateFunction;
import com.scallion.utils.FlinkUtil;
import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.*;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-05-18.
 * function: 窗口操作
 * origin ->
 */
public class WindowsJob implements Job {
    @Override
    public void run() {
        /**
         * Source
         */
        //点击日志
        SingleOutputStreamOperator<Tuple3<String, String, String>> clickStream = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.APP_NEWSAPP_TOPIC, Common.KAFKA_CONSUMER_GROUP_ID)
                .map(new MapFunction<String, Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> map(String input) throws Exception {
                        String[] split = input.split("\t");
                        String userKey = split[5];//用户key
                        String ct = split[10];//用户行为操作时间
                        String opa = split[11];//用户行为类型
                        return new Tuple3<>(userKey, ct, opa);
                    }
                });
        /**
         * Transform
         */
        //ReduceFunction
        SingleOutputStreamOperator<Tuple3<String, String, String>> reduceFunctionStream = clickStream
                .keyBy(tuple -> tuple.f0)
                .window(TumblingProcessingTimeWindows.of(Time.minutes(1)))
                .reduce(new ReduceFunction<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> reduce(Tuple3<String, String, String> input1, Tuple3<String, String, String> input2) throws Exception {
                        return new Tuple3<>(input1.f0, input1.f1, input2.f1);
                    }
                });
        //AggregateFunction
        SingleOutputStreamOperator<String> aggFunctionStream = clickStream
                .keyBy(tuple -> tuple.f0)
                .window(TumblingProcessingTimeWindows.of(Time.minutes(3)))
                .aggregate(new CountAggregateFunction());
        //使用ReduceFunction进行增量窗口聚合
        //获取窗口内事件行为操作的开始和结束时间和窗口的开始结束时间
        SingleOutputStreamOperator<String> reduceAndProcess = clickStream
                .keyBy(tuple -> tuple.f0)
                .window(TumblingProcessingTimeWindows.of(Time.minutes(3)))
                .reduce(new MyReduceFunction(), new ReduceProcessWindowFunction());
        /**
         * Sink
         */
//        reduceFunctionStream.print();
//        aggFunctionStream.print();
        reduceAndProcess.print();
    }

    private class MyReduceFunction implements ReduceFunction<Tuple3<String, String, String>> {
        @Override
        public Tuple3<String, String, String> reduce(Tuple3<String, String, String> input1, Tuple3<String, String, String> input2) throws Exception {
            return new Tuple3<>(input1.f0, input1.f1, input2.f1);
        }
    }

    private class ReduceProcessWindowFunction extends ProcessWindowFunction<Tuple3<String, String, String>, String, String, TimeWindow> {
        @Override
        public void process(String key, Context ctx, Iterable<Tuple3<String, String, String>> iterable, Collector<String> collector) throws Exception {
            Tuple3<String, String, String> tuple = iterable.iterator().next();
            collector.collect("record key:" + tuple.f0 + " start:" + tuple.f1 + " end:" + tuple.f2
                    + "----window start:" + TimeUtil.getTimestampToDate(ctx.window().getStart()) + " end:" + TimeUtil.getTimestampToDate(ctx.window().getEnd()));
        }
    }
}
