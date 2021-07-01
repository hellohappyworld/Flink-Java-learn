package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.eventtime.TimestampAssigner;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function:实时概况事件时间抽取器
 * origin ->
 */
public class RealTimeTimestampAssigner implements TimestampAssigner<PageAndInfoLogBean> {
    @Override
    public long extractTimestamp(PageAndInfoLogBean pageAndInfoLogBean, long l) {
        return TimeUtil.getDateToTimestamp(pageAndInfoLogBean.getNginxTm());
    }
}
