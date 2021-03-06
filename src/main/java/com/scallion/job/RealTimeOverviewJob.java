package com.scallion.job;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.bean.RealTimeOverviewResultBean;
import com.scallion.common.Common;
import com.scallion.transform.RealTimeFilterFunction;
import com.scallion.transform.RealTimeOverviewProcessWindowFunction;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.transform.RealTimeWatermarkStrategy;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function: 实时概况
 * origin ->
 */
public class RealTimeOverviewJob implements Job {
    @Override
    public void run() {
        //source
        //过滤出点击日志
        SingleOutputStreamOperator<String> clickJsonLog = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.PAGE_AND_INFO_TOPIC, Common.KAFKA_CONSUMER_GROUP_ID)
                .filter(new RealTimeFilterFunction("filterClickLog", Common.CLICKOPATYPES))
                .filter(new RealTimeFilterFunction("fliterJsonKey", "curpub", Common.CURPUB));
        //transform
        //json日志映射为对象
        SingleOutputStreamOperator<PageAndInfoLogBean> clickBeanLog = clickJsonLog
                .map(new RealTimeMapFunction(Common.JSONTOBEAN))
                .map(bean -> (PageAndInfoLogBean) bean) //父类转换成子类
                .assignTimestampsAndWatermarks(new RealTimeWatermarkStrategy().withIdleness(Duration.ofSeconds(10)));
        SingleOutputStreamOperator<RealTimeOverviewResultBean> result = clickBeanLog
                .keyBy(bean -> bean.getNginxTm().split(" ")[0].trim()) //按天分区，如：2021-06-23
                .window(TumblingEventTimeWindows.of(Time.minutes(1))) //1分钟的翻滚窗口
                .allowedLateness(Time.minutes(30)) //满足30分钟的迟到数据
                .process(new RealTimeOverviewProcessWindowFunction());

        //sink
        result.print();
    }
}
