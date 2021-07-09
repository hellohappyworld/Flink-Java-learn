package com.scallion.job;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.bean.RealTimeVideoPlayResultBean;
import com.scallion.common.Common;
import com.scallion.transform.RealTimeFilterFunction;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.transform.RealTimeVideoPlayProcessWindowFunction;
import com.scallion.transform.RealTimeWatermarkStrategy;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;
import java.util.ArrayList;

/**
 * created by gaowj.
 * created on 2021-07-01.
 * function: 实时播放job
 */
public class RealTimeVideoPlayJob implements Job {
    @Override
    public void run() {
        //Source
        ArrayList<String> filterConditions = new ArrayList<>();//过滤条件
        filterConditions.add(Common.OPATYPES.get("v"));
        filterConditions.add(Common.OPATYPES.get("pageinfo"));
        SingleOutputStreamOperator<String> clickAndInfoJsonLog = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.PAGE_AND_INFO_TOPIC, Common.VIDEO_PLAY_KAFKA_CONSUMER_GROUP_ID)
                .filter(new RealTimeFilterFunction("fliterJsonKey", "opa", filterConditions));
        //Transform
        //json日志映射为bean对象
        SingleOutputStreamOperator<PageAndInfoLogBean> clickAndInfoBeanLog = clickAndInfoJsonLog
                .map(new RealTimeMapFunction("jsonToBean"))
                .map(bean -> (PageAndInfoLogBean) bean) //父类转换成子类
                .assignTimestampsAndWatermarks(new RealTimeWatermarkStrategy().withIdleness(Duration.ofSeconds(10)));

        SingleOutputStreamOperator<RealTimeVideoPlayResultBean> result = clickAndInfoBeanLog
                .keyBy(bean -> bean.getNginxTm().split(" ")[0].trim()) //按天分区
                .window(TumblingEventTimeWindows.of(Time.minutes(1))) //1分钟的翻转窗口
                .allowedLateness(Time.minutes(30)) //30分钟的延迟数据
                .process(new RealTimeVideoPlayProcessWindowFunction());
        //Sink
        result.print();
    }
}
