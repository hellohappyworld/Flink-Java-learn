package com.scallion.job;

import com.scallion.common.Common;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-05-13.
 * function:
 * origin ->
 */
public class TwoStreamJoinJob implements Job {
    @Override
    public void run() {
        /**
         * source
         */
        //点击日志
        DataStream<String> clickStream = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.APP_NEWSAPP_TOPIC, Common.KAFKA_CONSUMER_GROUP_ID);
        //曝光日志
        DataStream<String> infoStream = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.APP_NEWSAPP_INFO_TOPIC, Common.KAFKA_CONSUMER_GROUP_ID);
        /**
         * transform
         */
        SingleOutputStreamOperator<Tuple2<String, String>> clickTupleStream = clickStream.map(new MapFunction<String, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> map(String record) throws Exception {
                String[] split = record.split("\t");
                //split[5]：userkey
                //split[11]：点击行为类型，常见为 action page duration btomnews
                Tuple2<String, String> tuple = new Tuple2<>(split[5], "click:" + split[11]);
                return tuple;
            }
        });
        SingleOutputStreamOperator<Tuple2<String, String>> infoTupleStream = infoStream.map(new MapFunction<String, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> map(String record) throws Exception {
                String[] split = record.split("\t");
                //split[5]：userkey
                //split[11]：曝光行为类型，当前只有pageinfo一种
                Tuple2<String, String> tuple = new Tuple2<>(split[5], "info:" + split[11]);
                return tuple;
            }
        });

        //1、inner join
        DataStream<String> innerJoinStream = clickTupleStream
                .join(infoTupleStream)
                .where(tuple -> tuple.f0) //点击日志流clickTupleStream的key
                .equalTo(tuple -> tuple.f0) //曝光日志流infoTupleStream的key
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .apply(new JoinFunction<Tuple2<String, String>, Tuple2<String, String>, String>() {
                    @Override
                    public String join(Tuple2<String, String> click, Tuple2<String, String> info) throws Exception {
                        return click.f0 + " " + click.f1 + " " + info.f1;
                    }
                });
        //2、left|right outer join
        //左连接
        DataStream<String> leftOutJoinStream = infoTupleStream
                .coGroup(clickTupleStream)
                .where(tuple -> tuple.f0)
                .equalTo(tuple -> tuple.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .apply(new CoGroupFunction<Tuple2<String, String>, Tuple2<String, String>, String>() {
                    @Override
                    public void coGroup(Iterable<Tuple2<String, String>> infoIterable, Iterable<Tuple2<String, String>> clickIterable, Collector<String> collector) throws Exception {
                        //遍历左流
                        for (Tuple2<String, String> infoRecord : infoIterable) {
                            boolean isMatched = false;
                            //遍历右流
                            for (Tuple2<String, String> clickRecord : clickIterable) {
                                //右流中有对应的记录
                                collector.collect(infoRecord.f0 + " " + infoRecord.f1 + " " + clickRecord.f1);
                                isMatched = true;
                            }
                            if (!isMatched) {
                                //右流中无数据
                                collector.collect(infoRecord.f0 + " " + infoRecord.f1 + " " + null);
                            }
                        }
                    }
                });
        //右连接
        DataStream<String> rightOutJoinStream = clickTupleStream
                .coGroup(infoTupleStream)
                .where(tuple -> tuple.f0)
                .equalTo(tuple -> tuple.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .apply(new CoGroupFunction<Tuple2<String, String>, Tuple2<String, String>, String>() {
                    @Override
                    public void coGroup(Iterable<Tuple2<String, String>> clickIterable, Iterable<Tuple2<String, String>> infoIterable, Collector<String> collector) throws Exception {
                        boolean isMatched = false;
                        //遍历右流
                        for (Tuple2<String, String> infoRecord : infoIterable) {
                            //遍历左流
                            for (Tuple2<String, String> clickRecord : clickIterable) {
                                //左流中对应的记录
                                collector.collect(infoRecord.f0 + " " + clickRecord.f1 + " " + infoRecord.f1);
                                isMatched = true;
                            }
                            if (!isMatched) {
                                //左流中无数据
                                collector.collect(infoRecord.f0 + " " + null + " " + infoRecord.f1);
                            }
                        }
                    }
                });
        //3、interval join
        SingleOutputStreamOperator<String> intervalJoinStream = infoTupleStream
                .keyBy(record -> record.f0)
                .intervalJoin(clickTupleStream.keyBy(record -> record.f0))
                .between(Time.seconds(-30), Time.seconds(30)) //指定右流相对左流偏移的时间区间
                .process(new ProcessJoinFunction<Tuple2<String, String>, Tuple2<String, String>, String>() {
                    @Override
                    public void processElement(Tuple2<String, String> infoRecord, Tuple2<String, String> clickRecord, Context context, Collector<String> collector) throws Exception {
                        collector.collect(infoRecord.f0 + " " + infoRecord.f1 + " " + clickRecord.f1);
                    }
                });

        /**
         * sink
         */
//        innerJoinStream.print();
//        leftOutJoinStream.print();
//        rightOutJoinStream.print();
        intervalJoinStream.print();
    }
}
