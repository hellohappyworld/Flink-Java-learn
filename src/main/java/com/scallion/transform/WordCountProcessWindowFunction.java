package com.scallion.transform;

import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-06-01.
 * function: 计算窗口内数据的count值
 * origin ->
 */
public class WordCountProcessWindowFunction extends ProcessWindowFunction<Tuple2<String, Long>, String, String, TimeWindow> {
    ValueState<Integer> state;
    int count;

    @Override
    public void open(Configuration parameters) throws Exception {
        //该状态值为全局状态，不随某个窗口的结束清理
        state = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("WordCountProcess test", Integer.class));
    }

    @Override
    public void clear(Context context) throws Exception {

    }

    @Override
    public void process(String key, Context context, Iterable<Tuple2<String, Long>> elements, Collector<String> collector) throws Exception {
        int inputSize = Iterables.size(elements);
        if (state.value() == null)
            count = inputSize;
        else
            count = state.value() + inputSize;
        state.update(count);
        String res = "key:" + key + "\t"
                + "windowStart:" + TimeUtil.getTimestampToDate(context.window().getStart()) + "\t"
                + "windowEnd:" + TimeUtil.getTimestampToDate(context.window().getEnd()) + "\t"
                + "inputSize:" + inputSize + "\t"
                + "allCount:" + count;
        collector.collect(res);
    }
}
