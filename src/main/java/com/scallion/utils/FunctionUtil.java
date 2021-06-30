package com.scallion.utils;

import com.scallion.common.Common;

import java.util.Iterator;

/**
 * created by gaowj.
 * created on 2021-06-30.
 * function: 普通通用函数
 */
public class FunctionUtil {
    /**
     * 获取平台类型
     *
     * @param mos
     * @return
     */
    public static String getMos(String mos) {
        String platType = "other";

        Iterator<String> platIterator = Common.PLATFORM.iterator();
        while (platIterator.hasNext()) {
            String plat = platIterator.next();
            if (mos.contains(plat)) {
                platType = plat;
                break;
            }
        }

        return platType;
    }
}
