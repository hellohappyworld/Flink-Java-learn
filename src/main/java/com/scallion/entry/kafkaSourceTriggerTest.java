package com.scallion.entry;

import com.scallion.transform.CustomProcessingTimeTrigger;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

/**
 * created by gaowj.
 * created on 2021-05-24.
 * function: 窗口触发器示例代码入口
 * origin ->
 */
public class kafkaSourceTriggerTest {
    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        AllWindowedStream<Integer, TimeWindow> stream = env.socketTextStream("10.90.126.150", 9999)
                .map(new String2Integer())
                .timeWindowAll(Time.seconds(60))
                .trigger(CustomProcessingTimeTrigger.create());
        stream.sum(0)
                .print();

        env.execute("Flink Streaming Java API Skeleton");
    }

    private static class String2Integer extends RichMapFunction<String, Integer> {
        private static final long serialVersionUID = 1180234853172462378L;

        @Override
        public Integer map(String event) throws Exception {

            return Integer.valueOf(event);
        }

        @Override
        public void open(Configuration parameters) throws Exception {
        }
    }

}
