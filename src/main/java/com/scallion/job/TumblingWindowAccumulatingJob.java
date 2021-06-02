package com.scallion.job;

import com.scallion.transform.BoundedOutOfOrdernessGenerator;
import com.scallion.transform.MyWatermarkStrategy;
import com.scallion.transform.WordCountProcessWindowFunction;
import com.scallion.utils.FlinkUtil;
import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Iterator;

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
                .assignTimestampsAndWatermarks(new MyWatermarkStrategy());

        //允许3秒的乱序
        SingleOutputStreamOperator<String> outDS = watermarkDS
                .keyBy(tuple -> tuple.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(30)))
                .allowedLateness(Time.seconds(60))
                .process(new WordCountProcessWindowFunction());
        /**
         * Sink
         */
        outDS.print();
    }
}
