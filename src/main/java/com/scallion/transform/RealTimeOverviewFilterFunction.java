package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
            JSONObject jsonObj = JSON.parseObject(input);
            switch (filterType) {
                case "filterClickLog": {
                    //用户行为标志
                    String opa = jsonObj.getOrDefault("opa", "#").toString();
                    if (!Common.CLICKOPATYPES.contains(opa))
                        return true;
                    else
                        return false;
                }
                case "filterCurpub": {
                    //发布版本号，publishid
                    String curpub = jsonObj.getOrDefault("curpub", "#").toString();
                    if (Common.CURPUB.contains(curpub))
                        return false;
                    else
                        return true;
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
