package com.scallion.entry;

import com.scallion.job.TwoStreamJoinJob;
import com.scallion.utils.FlinkUtil;


/**
 * created by gaowj.
 * created on 2021-05-13.
 * function: 双流join
 * origin ->
 */
public class TwoStreamJoinEntry {
    public static void main(String[] args) throws Exception {
        FlinkUtil.run(new TwoStreamJoinJob());
        FlinkUtil.execution("TwoStreamJoin");
    }
}
