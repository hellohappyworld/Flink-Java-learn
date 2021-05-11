package com.scallion.utils;

import com.scallion.job.Job;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.concurrent.TimeUnit;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function: Flink上下文配置，源数据获取类
 * origin ->
 */
public class FlinkUtil {
    private static StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    static {
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.enableCheckpointing(1000);
        env.setParallelism(10);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
                3,
                Time.of(10, TimeUnit.SECONDS)
        ));
    }

    public static void run(Job job) {
        job.run();
    }

    public static DataStream<String> getSocketTextStream(String ip, int port) {
        DataStreamSource<String> source = env.socketTextStream(ip, port);
//        DataStreamSource<String> source = env.fromElements("a a a b c c c d d f h");
        return source;
    }

    public static void execution(String jobName) {
        try {
            env.execute(jobName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
