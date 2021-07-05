package com.scallion.job;

import com.scallion.common.Common;
import com.scallion.transform.PageAndInfoBeansFilterFunction;
import com.scallion.transform.RealTimeFilterFunction;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

import java.util.ArrayList;

/**
 * created by gaowj.
 * created on 2021-07-02.
 * function:实时点击排行任务
 */
public class RealTimeClickRankJob implements Job {
    @Override
    public void run() {
        //Source
        ArrayList<String> opaList = new ArrayList<>();
        opaList.add("page");
        opaList.add("v");
        opaList.add("action");
        opaList.add("ts");
        opaList.add("pageinfo");
        SingleOutputStreamOperator<String> clickAndInfoLogs = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.PAGE_AND_INFO_TOPIC, Common.CLICK_RANK_KAFKA_CONSUMER_GROUP_ID)
                .filter(new RealTimeFilterFunction("fliterJsonKey", "opa", opaList));
        //Transform
        clickAndInfoLogs
                .map(new RealTimeMapFunction("jsonToBean"))
                .filter(new PageAndInfoBeansFilterFunction(""));
        //Sink
    }
}
