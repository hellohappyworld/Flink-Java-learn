package com.scallion.entry.dim;

import com.scallion.job.DimAccountJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-06.
 * function: 账号维表入口
 */
public class DimAccountEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new DimAccountJob());
        FlinkUtil.execution("DimAccountEntry");
    }
}
