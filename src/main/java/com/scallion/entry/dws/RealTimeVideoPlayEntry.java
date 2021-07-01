package com.scallion.entry.dws;

import com.scallion.job.RealTimeVideoPlayJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-01.
 * function: 实时播放入口类
 */
public class RealTimeVideoPlayEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new RealTimeVideoPlayJob());
        FlinkUtil.execution("RealTimeVideoPlayEntry");
    }
}
