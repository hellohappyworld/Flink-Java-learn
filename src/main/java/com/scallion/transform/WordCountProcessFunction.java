package com.scallion.transform;

import com.scallion.bean.CountWithTimestampState;
import com.scallion.bean.WordWithCount;
import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-03-10.
 * function:
 * origin ->
 */
public class WordCountProcessFunction extends KeyedProcessFunction<String, WordWithCount, String> {
    //处理函数负责维护的状态
    private ValueState<CountWithTimestampState> state;

    //首先获得由这个处理函数(process function)维护的状态
    @Override
    public void open(Configuration parameters) throws Exception {
        state = getRuntimeContext().getState(new ValueStateDescriptor<CountWithTimestampState>("myState", CountWithTimestampState.class));
    }

    //对于在输入流中接收到的每个事件，此函数就会被调用以处理该函数
    //对于每个记录，KeyedProcessFunction递增计数器并设置最后修改时间戳
    @Override
    public void processElement(WordWithCount input, Context context, Collector<String> collector) throws Exception {
        //获取当前的计数
        CountWithTimestampState current = state.value();
        if (current == null) {
            current = new CountWithTimestampState();
            current.setKey(input.getKey());
        }
        //更新状态计数值
        current.setCount(current.getCount() + 1);
        //设置该状态的时间戳为记录的分配的时间时间时间戳
        if (context != null)
            current.setLastModified(context.timestamp());
        //将状态写回
        state.update(current);
        //从当前事件时间开始安排下一个计时器10秒
        context.timerService().registerEventTimeTimer(current.getLastModified() + 10000);
    }

    //如果10秒内没有进一步的更新，则发出 key count 对
    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
        //获取调度此计时器的key的状态
        CountWithTimestampState result = state.value();
        //检查这是一个过时的计时器还是最新的计时器
        if (timestamp == result.getLastModified() + 10000)
            out.collect(result.getKey() + "\t" + result.getCount() + "\t" + TimeUtil.getTimestampToDate(result.getLastModified()));
    }
}
