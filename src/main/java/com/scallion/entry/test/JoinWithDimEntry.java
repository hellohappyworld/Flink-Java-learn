package com.scallion.entry.test;

import com.scallion.job.JoinWithDimJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-05-14.
 * function: 维表Join
 * origin ->
 */
public class JoinWithDimEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new JoinWithDimJob());
        FlinkUtil.execution("JoinWithDimEntry");
    }
}
