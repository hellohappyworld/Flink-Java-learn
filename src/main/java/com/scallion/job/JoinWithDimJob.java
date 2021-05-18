package com.scallion.job;

import com.scallion.common.Common;
import com.scallion.transform.AsyncIOMySQLFunction;
import com.scallion.transform.DimBroadcastProcessFunction;
import com.scallion.transform.JoinWithDimMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.util.concurrent.TimeUnit;

/**
 * created by gaowj.
 * created on 2021-05-14.
 * function: 维表Join
 * origin ->
 */
public class JoinWithDimJob implements Job {
    @Override
    public void run() {
        /**
         * Source
         */
        //点击日志
        DataStream<String> clickStream = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.APP_NEWSAPP_TOPIC, Common.KAFKA_CONSUMER_GROUP_ID);
        //维表数据
        SingleOutputStreamOperator<Tuple2<String, String>> socketDimStream = FlinkUtil.getSocketTextStream(Common.SOCKET_IP, Common.SOCKET_PORT)
                .map(new MapFunction<String, Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> map(String line) throws Exception {
                        String[] split = line.split(",");
                        return new Tuple2<String, String>(split[0], split[1]);
                    }
                });
        /**
         * Transform
         */
        //1、预加载维表
        SingleOutputStreamOperator<String> joinWithDimStream = clickStream.map(new JoinWithDimMapFunction());
        //2、热存储维表:使用异步IO来提高访问吞吐量
        SingleOutputStreamOperator<String> asyncIOStream = AsyncDataStream
                .orderedWait(clickStream, new AsyncIOMySQLFunction(), 1000L, TimeUnit.MILLISECONDS, 10);
        //3、广播维表
        //将维表数据流定义为广播流
        MapStateDescriptor broadcastDesc = new MapStateDescriptor("broad1", String.class, String.class);
        BroadcastStream<Tuple2<String, String>> broadcastStream = socketDimStream.broadcast(broadcastDesc);
        SingleOutputStreamOperator<String> broadcastWithDimStream = clickStream
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String line) throws Exception {
                        return line.split("\t")[5].trim();
                    }
                })
                .keyBy(line -> line)
                .window(ProcessingTimeSessionWindows.withGap(Time.seconds(30)))
                .reduce((line1, line2) -> line1)
                .connect(broadcastStream)
                .process(new DimBroadcastProcessFunction(broadcastDesc));
        //4、临时表函数Join
        /**
         * Sink
         */
//        joinWithDimStream.print();
//        asyncIOStream.print();
        broadcastWithDimStream.print();
    }
}
