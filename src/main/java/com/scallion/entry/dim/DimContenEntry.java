package com.scallion.entry.dim;

import com.scallion.job.DimContentJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-12.
 * function: 内容维表入口
 */
public class DimContenEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new DimContentJob());
        FlinkUtil.execution("DimContenEntry");
    }
}
