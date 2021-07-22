package com.scallion.entry.dim;

import com.scallion.job.DimUserJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-22.
 * function:用户维表计算入口
 */
public class DimUserEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new DimUserJob());
        FlinkUtil.execution("DimUserEntry");
    }
}
