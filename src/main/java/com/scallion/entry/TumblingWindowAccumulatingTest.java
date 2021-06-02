package com.scallion.entry;

import com.scallion.job.TumblingWindowAccumulatingJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-05-28.
 * function: watermark  allowedLateness  RichWindowFunction
 * origin -> https://blog.csdn.net/lmalds/article/details/55259718
 */
public class TumblingWindowAccumulatingTest {
    public static void main(String[] args) {
        FlinkUtil.run(new TumblingWindowAccumulatingJob());
        FlinkUtil.execution("TumblingWindowAccumulatingTest");
    }
}
