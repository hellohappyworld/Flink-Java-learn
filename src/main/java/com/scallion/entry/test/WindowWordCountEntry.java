package com.scallion.entry.test;

import com.scallion.job.WindowWordCountJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function: 入口类
 * origin ->
 */
public class WindowWordCountEntry {
    public static void main(String[] args) {
        String ip = args[0];
//        String ip = "";
        int port = Integer.parseInt(args[1]);
//        int port = 0;
        FlinkUtil.run(new WindowWordCountJob(ip, port));
        FlinkUtil.execution("WindowWordCountEntry");
    }
}
