package com.scallion.entry.dwd;

import com.scallion.job.DwdDivideOpaJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-22.
 * function: 按照用户行为(opa)建模划分dwd层明细数据
 */
public class DwdDivideOpaEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new DwdDivideOpaJob());
        FlinkUtil.execution("DwdDivideOpaEntry");
    }
}
