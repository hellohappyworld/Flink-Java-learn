package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.scallion.bean.PageAndInfoLogBean;
import org.apache.flink.api.common.functions.RichMapFunction;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function:实时概况转换函数
 * origin ->
 */
public class RealTimeMapFunction extends RichMapFunction<String, PageAndInfoLogBean> {
    //转换类型
    private String mapType;

    public RealTimeMapFunction() {
    }

    public RealTimeMapFunction(String mapType) {
        this.mapType = mapType;
    }

    @Override
    public PageAndInfoLogBean map(String input) throws Exception {
        try {
            switch (mapType) {
                case "jsonToBean":
                    return JSON.parseObject(input, PageAndInfoLogBean.class);
                default:
                    return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("RealTimeMapFunction err:" + input);
            return null;
        }
    }
}
