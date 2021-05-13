package com.scallion.entry;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;

/**
 * created by gaowj.
 * created on 2021-05-11.
 * function: ProcessWindowFunction示例
 * origin -> https://www.cnblogs.com/linjiqin/p/12591729.html
 */
public class ProcessWindowFunctionEntry {
    public static final Tuple3[] ENGLISH = new Tuple3[]{
            Tuple3.of("class1", "张三", 1L),
            Tuple3.of("class1", "李四", 1L),
            Tuple3.of("class1", "王五", 1L),
            Tuple3.of("class2", "赵六", 1L),
            Tuple3.of("class2", "小七", 1L),
            Tuple3.of("class2", "小八", 1L),
            Tuple3.of("class3", "小七", 1L),
    };

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Tuple3<String, String, Long>> input = env.fromElements(ENGLISH);
        SingleOutputStreamOperator<String> avgScore = input.keyBy(0)
                .countWindow(3)
                .process(new MyProcessWindowFunction());
        avgScore.print();

        env.execute("ProcessWindowFunctionEntry");
    }

    public static class MyProcessWindowFunction extends ProcessWindowFunction<Tuple3<String, String, Long>, String, Tuple, GlobalWindow> {
        /**
         * @param tuple     此处的tuple其实就是Tuple3中的第一个字段，也就是keyBy(0)所选择的键
         * @param context
         * @param iterable
         * @param collector
         * @throws Exception
         */
        @Override
        public void process(Tuple tuple, Context context, Iterable<Tuple3<String, String, Long>> iterable, Collector<String> collector) throws Exception {
            int sum = 0;
            int count = 0;
            for (Tuple3<String, String, Long> in : iterable) {
                sum += in.f2;
                count++;
            }

            collector.collect(tuple.getField(0) + ":" + String.valueOf(count));
        }
    }
}
