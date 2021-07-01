package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scallion.common.Common;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.RichFilterFunction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function: 实时概况过滤函数
 * origin ->
 */
public class RealTimeFilterFunction extends RichFilterFunction<String> {
    //过滤类型
    private String filterType;
    //需要过滤的jsonKey
    private String jsonKey;
    //过滤条件
    private Collection filterConditions;

    public RealTimeFilterFunction() {

    }

    public RealTimeFilterFunction(String filterType) {
        this.filterType = filterType;
    }

    public RealTimeFilterFunction(String filterType, Collection filterConditions) {
        this.filterType = filterType;
        this.filterConditions = filterConditions;
    }

    public RealTimeFilterFunction(String filterType, String jsonKey, Collection filterConditions) {
        this.filterType = filterType;
        this.jsonKey = jsonKey;
        this.filterConditions = filterConditions;
    }

    @Override
    public boolean filter(String input) throws Exception {
        try {
            JSONObject jsonObj = JSON.parseObject(input);
            switch (filterType) {
                case "fliterJsonKey": {
                    String jsonValue = jsonObj.getOrDefault(jsonKey, "#").toString();
                    if (filterConditions.contains(jsonValue))
                        return true;
                    else
                        return false;
                }
                case "filterClickLog": {
                    //用户行为标志
                    String opa = jsonObj.getOrDefault("opa", "#").toString();
                    if (!filterConditions.contains(opa))
                        return true;
                    else
                        return false;
                }
                default:
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("RealTimeFilterFunction err:" + input);
            return false;
        }
    }
}
