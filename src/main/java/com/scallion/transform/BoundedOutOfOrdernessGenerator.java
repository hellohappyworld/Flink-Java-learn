package com.scallion.transform;

import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * created by gaowj.
 * created on 2021-06-01.
 * function: 水印生成器
 * origin ->
 */
public class BoundedOutOfOrdernessGenerator implements WatermarkGenerator<Tuple2<String, Long>> {
    private final long maxOutOfOrderness = 30000; // 允许3秒的乱序
    private long currentMaxTimestamp;

    @Override
    public void onEvent(Tuple2<String, Long> event, long eventTimestamp, WatermarkOutput output) {
//        currentMaxTimestamp = Math.max(currentMaxTimestamp, event.f1);
        currentMaxTimestamp = Math.max(currentMaxTimestamp, eventTimestamp);
    }

    @Override
    public void onPeriodicEmit(WatermarkOutput output) {
        output.emitWatermark(new Watermark(currentMaxTimestamp - maxOutOfOrderness));
    }
}
