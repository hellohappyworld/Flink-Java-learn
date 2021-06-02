package com.scallion.transform;

import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * created by gaowj.
 * created on 2021-06-01.
 * function: 水印策略
 * origin ->
 */
public class MyWatermarkStrategy implements WatermarkStrategy<Tuple2<String, Long>> {
    //抽取事件时间
    @Override
    public TimestampAssigner<Tuple2<String, Long>> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return new MyTimestampAssigner();
    }

    //发射水印
    @Override
    public WatermarkGenerator<Tuple2<String, Long>> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new BoundedOutOfOrdernessGenerator();
    }
}
