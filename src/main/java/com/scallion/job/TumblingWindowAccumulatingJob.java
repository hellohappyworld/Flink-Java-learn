package com.scallion.job;

import com.scallion.transform.MyWatermarkStrategy;
import com.scallion.transform.WordCountProcessWindowFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;

/**
 * created by gaowj.
 * created on 2021-05-28.
 * function: watermark  allowedLateness  RichWindowFunction
 * origin -> https://blog.csdn.net/lmalds/article/details/55259718
 */
public class TumblingWindowAccumulatingJob implements Job {
    @Override
    public void run() {
        /**
         * Source
         */
        DataStream<String> sourceDS = FlinkUtil.getSocketTextStream();
        /**
         * Transform
         */
        SingleOutputStreamOperator<Tuple2<String, Long>> inputMapDS = sourceDS
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String input) throws Exception {
                        String[] split = input.split(",");
                        return new Tuple2<>(split[0], Long.parseLong(split[1]));
                    }
                });
        SingleOutputStreamOperator<Tuple2<String, Long>> watermarkDS = inputMapDS
                .assignTimestampsAndWatermarks(new MyWatermarkStrategy().withIdleness(Duration.ofSeconds(10))); //允许30秒的乱序，1秒的空闲检测

        SingleOutputStreamOperator<String> outDS = watermarkDS
                .keyBy(tuple -> tuple.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(30))) //30秒翻滚窗口大小
                .allowedLateness(Time.seconds(60)) //允许60秒的延迟数据
                .process(new WordCountProcessWindowFunction());
        /**
         * Sink
         */
        outDS.print();
    }
}
