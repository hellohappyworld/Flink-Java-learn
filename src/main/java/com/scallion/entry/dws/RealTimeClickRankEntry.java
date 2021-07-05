package com.scallion.entry.dws;

import com.scallion.job.RealTimeClickRankJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-02.
 * function:实时点击排行
 */
public class RealTimeClickRankEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new RealTimeClickRankJob());
        FlinkUtil.execution("RealTimeClickRankEntry");
    }
}
