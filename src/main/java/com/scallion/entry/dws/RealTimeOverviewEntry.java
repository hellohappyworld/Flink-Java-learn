package com.scallion.entry.dws;

import com.scallion.job.RealTimeOverviewJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function: 实时概况
 * origin ->
 */
public class RealTimeOverviewEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new RealTimeOverviewJob());
        FlinkUtil.execution("RealTimeOverviewEntry");
    }
}
