package com.scallion.entry.dim;

import com.scallion.job.DimContentFeaturesJob;
import com.scallion.utils.FlinkUtil;

/**
 * created by gaowj.
 * created on 2021-07-08.
 * function: 内容画像维表入口
 */
public class DimContentFeaturesEntry {
    public static void main(String[] args) {
        FlinkUtil.run(new DimContentFeaturesJob());
        FlinkUtil.execution("DimContentFeaturesEntry");
    }
}
