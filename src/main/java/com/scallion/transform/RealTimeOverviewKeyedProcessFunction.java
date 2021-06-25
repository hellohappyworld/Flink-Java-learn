package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.bean.RealTimeOverviewResultBean;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-06-24.
 * function: 实时概况process类
 * origin ->
 */
public class RealTimeOverviewKeyedProcessFunction extends ProcessWindowFunction<PageAndInfoLogBean, RealTimeOverviewResultBean, String, TimeWindow> {

    @Override
    public void process(String key, Context context, Iterable<PageAndInfoLogBean> inputIterable, Collector<RealTimeOverviewResultBean> out) throws Exception {

    }
}
