package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import org.apache.flink.api.common.functions.RichFilterFunction;

/**
 * created by gaowj.
 * created on 2021-07-02.
 * function:
 * origin ->
 */
public class PageAndInfoBeansFilterFunction extends RichFilterFunction<Object> {
    //过滤类型
    private String filterType;

    public PageAndInfoBeansFilterFunction(String filterType) {
        this.filterType = filterType;
    }

    @Override
    public boolean filter(Object logBean) throws Exception {
        try {
            switch (filterType) {
                case "": {
                    PageAndInfoLogBean PageAndInfoLogBean = (PageAndInfoLogBean) logBean;
                    return false;
                }
                default:
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
