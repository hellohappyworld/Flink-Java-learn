package com.scallion.job;

import com.scallion.bean.WordWithCount;
import com.scallion.transform.WordCountProcessFunction;
import com.scallion.transform.SplitterFlatMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.AscendingTimestampExtractor;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function:
 * origin ->
 */
public class WindowWordCountJob implements Job {
    private String ip;
    private int port;

    public WindowWordCountJob(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        /**
         * source
         */
        DataStream<String> source = FlinkUtil.getSocketTextStream(ip, port);
        /**
         * transform
         */
        // 数据切分
        SingleOutputStreamOperator<WordWithCount> splitDS = source.flatMap(new SplitterFlatMapFunction()).setParallelism(1);
        // 注册水印
        SingleOutputStreamOperator<WordWithCount> withTimestampsAndWatermarksDS = splitDS.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<WordWithCount>() {
            @Override
            public long extractAscendingTimestamp(WordWithCount windowCount) {
                return System.currentTimeMillis();
            }
        }).setParallelism(1);
        // 累加
        SingleOutputStreamOperator<String> resultDS = withTimestampsAndWatermarksDS
                .keyBy(tup -> tup.getKey())
                .process(new WordCountProcessFunction()).setParallelism(1);
        /**
         * sink
         */
        resultDS.print().setParallelism(1);
    }
}
