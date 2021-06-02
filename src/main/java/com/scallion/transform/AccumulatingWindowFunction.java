package com.scallion.transform;

import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.text.SimpleDateFormat;

/**
 * created by gaowj.
 * created on 2021-05-28.
 * function: 计算窗口内的元素的个数以及累计个数
 * origin ->
 */
public class AccumulatingWindowFunction extends RichWindowFunction<Tuple2<String, Long>, String, String, TimeWindow> {
    ValueState<Integer> state;
    int count;

    @Override
    public void open(Configuration parameters) throws Exception {
        state = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("AccumulatingWindow Test", Integer.class));
    }

    @Override
    public void apply(String key, TimeWindow window, Iterable<Tuple2<String, Long>> input, Collector<String> out) throws Exception {
        int inputSize = Iterables.size(input);
        if (state.value() == null)
            count = inputSize;
        else
            count = state.value() + inputSize;
        state.update(count);
        String res = "key:" + key + "\t"
                + "windowStart:" + TimeUtil.getTimestampToDate(window.getStart()) + "\t"
                + "windowEnd:" + TimeUtil.getTimestampToDate(window.getEnd()) + "\t"
                + "inputSize:" + inputSize + "\t"
                + "nowTime:" + TimeUtil.getTimestampToDate(System.currentTimeMillis()) + "\t"
                + "allCount:" + count;
        System.out.println("ceshi:" + res);
        out.collect(res);
    }
}
