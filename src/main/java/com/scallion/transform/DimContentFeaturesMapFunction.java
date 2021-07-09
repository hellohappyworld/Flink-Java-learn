package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scallion.bean.DimContentFeaturesBean;
import org.apache.flink.api.common.functions.RichMapFunction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * created by gaowj.
 * created on 2021-07-08.
 * function: 内容画像维表转换函数
 */
public class DimContentFeaturesMapFunction extends RichMapFunction<String, DimContentFeaturesBean> {
    @Override
    public DimContentFeaturesBean map(String log) throws Exception {
        String docid = "#";//文章ID
        String modifytime;//修改时间
        JSONArray scfeatures = new JSONArray();//sc标签
        JSONArray cfeatures = new JSONArray();//c标签
        JSONArray otherfeatures = new JSONArray();//其他标签
        String importdate;//导入时间
        String otherState = "#";//other state value
        String expiretime;//过期时间
        String searchpath;//栏目
        JSONArray classv = new JSONArray();//图谱分类

        DimContentFeaturesBean resultBean = new DimContentFeaturesBean();
        try {
            JSONObject jsonObj = JSON.parseObject(log);
            String other = jsonObj.getString("other");
            String[] others = other.split("\\|!\\|");
            for (String str : others) {
                if (str.startsWith("arcileId") && str.split("=").length == 2)
                    docid = str.split("=")[1];
                if (str.startsWith("state") && str.split("=").length == 2)
                    otherState = str.split("=")[1];
            }
            modifytime = jsonObj.getOrDefault("modifyTime", "#").toString();
            JSONArray features = jsonObj.getJSONArray("features");
            int size = features.size();
            int flag = 0;
            while (flag < size - 2) {
                String tag = features.getString(flag);
                String typ = features.getString(flag + 1);
                String rate = features.getString(flag + 2);
                flag = flag + 3;
                HashMap<String, String> jsonMap = new HashMap<>();
                jsonMap.put("tag", tag);
                jsonMap.put("typ", typ);
                jsonMap.put("rate", rate);
                String jsonStr = JSONObject.toJSONString(jsonMap);
                if ("sc".equals(typ))
                    scfeatures.add(jsonStr);
                else if ("c".equals(typ))
                    cfeatures.add(jsonStr);
                else
                    otherfeatures.add(jsonStr);
            }
            importdate = jsonObj.getOrDefault("importdate", "#").toString();
            expiretime = jsonObj.getOrDefault("expireTime", "#").toString();
            searchpath = jsonObj.getOrDefault("searchpath", "#").toString();
            if (jsonObj.containsKey("classV")) {
                String classVStr = jsonObj.getString("classV");
                try {
                    String classVStr1 = classVStr.substring(1, classVStr.length() - 1);
                    String[] classVarr = classVStr1.split(",");
                    for (String a : classVarr)
                        classv.add(a.substring(1, a.length() - 1));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            resultBean.setDocid(docid);
            resultBean.setModifytime(modifytime);
            resultBean.setScfeatures(scfeatures.toJSONString());
            resultBean.setCfeatures(cfeatures.toJSONString());
            resultBean.setOtherfeatures(otherfeatures.toJSONString());
            resultBean.setImportdate(importdate);
            resultBean.setOther_state(otherState);
            resultBean.setExpiretime(expiretime);
            resultBean.setSearchpath(searchpath);
            resultBean.setClassv(classv.toJSONString());
            return resultBean;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
