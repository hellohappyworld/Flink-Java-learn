package com.scallion.transform;

import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-05-17.
 * function: 广播维表
 * origin ->
 */
public class DimBroadcastProcessFunction extends BroadcastProcessFunction<String, Tuple2<String, String>, String> {
    MapStateDescriptor<String, String> broadcastDesc;

    public DimBroadcastProcessFunction(MapStateDescriptor<String, String> broadcastDesc) {
        this.broadcastDesc = broadcastDesc;
    }

    //非广播流调用
    @Override
    public void processElement(String input, ReadOnlyContext ctx, Collector<String> collector) throws Exception {
        //获取广播流数据
        ReadOnlyBroadcastState<String, String> state = ctx.getBroadcastState(broadcastDesc);
        String cityName = "";
        if (state.contains(input))
            cityName = state.get(input);
        collector.collect("userKey:" + input + " city:" + cityName);
    }

    //广播流调用
    @Override
    public void processBroadcastElement(Tuple2<String, String> input, Context ctx, Collector<String> collector) throws Exception {
        //将维表数据更新到广播流中
        System.out.println("收到广播数据：" + input);
        ctx.getBroadcastState(broadcastDesc).put(input.f0, input.f1);
    }
}
