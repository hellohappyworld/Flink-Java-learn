package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import org.apache.flink.api.common.eventtime.*;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function: 实时概况水印策略
 * origin ->
 */
public class RealTimeOverviewWatermarkStrategy implements WatermarkStrategy<PageAndInfoLogBean> {
    //抽取事件时间
    @Override
    public TimestampAssigner<PageAndInfoLogBean> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return new RealTimeOverviewTimestampAssigner();
    }

    //发射水印
    @Override
    public WatermarkGenerator<PageAndInfoLogBean> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new RealTimeOverviewWatermarkGenerator();
    }
}
