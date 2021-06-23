package com.scallion.entry.test;

import com.scallion.job.WindowsJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-05-18.
 * function: 窗口操作
 * origin ->
 */
public class WindowsEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new WindowsJob());
        FlinkUtil.execution("WindowsEntry");
    }
}
