package com.scallion.entry.dwd;

import com.scallion.job.OdsLogTmpJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-13.
 * function:明细数据层Entry
 */
public class OdsLogTmpEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new OdsLogTmpJob());
        FlinkUtil.execution("OdsLogTmpEntry");
    }
}
