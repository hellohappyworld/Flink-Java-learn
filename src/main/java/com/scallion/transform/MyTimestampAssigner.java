package com.scallion.transform;

import org.apache.flink.api.common.eventtime.TimestampAssigner;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * created by gaowj.
 * created on 2021-06-01.
 * function: 指定事件时间
 * origin ->
 */
public class MyTimestampAssigner implements TimestampAssigner<Tuple2<String, Long>> {
    @Override
    public long extractTimestamp(Tuple2<String, Long> event, long l) {
        return event.f1;
    }
}
