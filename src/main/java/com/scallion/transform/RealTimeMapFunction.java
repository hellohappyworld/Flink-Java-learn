package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.scallion.bean.DimAccountBean;
import com.scallion.bean.DimContentBean;
import com.scallion.bean.OdsLogTmpBean;
import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.common.Common;
import org.apache.flink.api.common.functions.RichMapFunction;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function:实时概况转换函数
 * origin ->
 */
public class RealTimeMapFunction extends RichMapFunction<String, Object> {
    //转换类型
    private String mapType;

    public RealTimeMapFunction() {
    }

    public RealTimeMapFunction(String mapType) {
        this.mapType = mapType;
    }

    @Override
    public Object map(String input) throws Exception {
        try {
            switch (mapType) {
                case Common.JSONTOBEAN:
                    return JSON.parseObject(input, PageAndInfoLogBean.class);
                case Common.ACCOUNTJSONTOBEAN:
                    return JSON.parseObject(input, DimAccountBean.class);
                case Common.CONTENTJSONTOBEAN: {
                    DimContentBean dimContentBean = JSON.parseObject(input, DimContentBean.class);
                    //判断id
                    switch (dimContentBean.getPageType()) {
                        //文章数据
                        case "article":
                            dimContentBean.setId("ucms_" + dimContentBean.getId());
                            dimContentBean.setJoinId("ucms_" + dimContentBean.getId());
                            break;
                        //视频数据
                        case "video":
                            dimContentBean.setId("video_" + dimContentBean.getId());
                            dimContentBean.setJoinId("video_" + dimContentBean.getId());
                            break;
                        //专项数据就是id本身,joinId为"#"
                    }

                    return dimContentBean;
                }
                case Common.FLOW_LOGS_JSON_TO_BEAN:
                    return JSON.parseObject(input, OdsLogTmpBean.class);
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
