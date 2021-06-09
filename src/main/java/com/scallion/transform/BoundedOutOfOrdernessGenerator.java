package com.scallion.transform;

import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * created by gaowj.
 * created on 2021-06-01.
 * function: 该Watermark生成器可以覆盖的场景：数据在一定程度上乱序
 */
public class BoundedOutOfOrdernessGenerator implements WatermarkGenerator<Tuple2<String, Long>> {
    private final long maxOutOfOrderness = 30000; // 允许30秒的乱序
    private long currentMaxTimestamp;

    @Override
    public void onEvent(Tuple2<String, Long> event, long eventTimestamp, WatermarkOutput output) {
        currentMaxTimestamp = Math.max(currentMaxTimestamp, eventTimestamp);
    }

    @Override
    public void onPeriodicEmit(WatermarkOutput output) {
        output.emitWatermark(new Watermark(currentMaxTimestamp - maxOutOfOrderness));
    }
}
