package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.scallion.common.Common;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.RichFilterFunction;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function: 实时概况过滤函数
 * origin ->
 */
public class RealTimeOverviewFilterFunction extends RichFilterFunction<String> {
    //过滤类型
    private String filterType;

    public RealTimeOverviewFilterFunction() {

    }

    public RealTimeOverviewFilterFunction(String filterType) {
        this.filterType = filterType;
    }

    @Override
    public boolean filter(String input) throws Exception {
        try {
            switch (filterType) {
                case "filterClickLog": {
                    String opa = JSON.parseObject(input).getString("opa").trim();
                    if (!Common.clickOpaTypes.contains(opa))
                        return true;
                    else
                        return false;
                }
                default:
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("RealTimeOverviewFilterFunction err:" + input);
            return false;
        }
    }
}
