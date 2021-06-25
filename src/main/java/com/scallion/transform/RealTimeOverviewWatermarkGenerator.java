package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function:实时概况水印发射器
 * origin ->
 */
public class RealTimeOverviewWatermarkGenerator implements WatermarkGenerator<PageAndInfoLogBean> {
    private final long maxOutOfOrderness = 1000; //1秒的乱序
    private long currentMaxTimestamp;

    @Override
    public void onEvent(PageAndInfoLogBean pageAndInfoLogBean, long eventTimestamp, WatermarkOutput watermarkOutput) {
        currentMaxTimestamp = Math.max(currentMaxTimestamp, eventTimestamp);
    }

    @Override
    public void onPeriodicEmit(WatermarkOutput output) {
        output.emitWatermark(new Watermark(currentMaxTimestamp - maxOutOfOrderness));
    }
}
