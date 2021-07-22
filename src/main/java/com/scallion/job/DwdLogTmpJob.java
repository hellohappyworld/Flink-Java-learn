package com.scallion.job;

import com.alibaba.fastjson.JSON;
import com.scallion.bean.OdsLogTmpBean;
import com.scallion.common.Common;
import com.scallion.sink.HBaseSink;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

import java.util.Properties;

/**
 * created by gaowj.
 * created on 2021-07-13.
 * function: 明细层Job
 */
public class DwdLogTmpJob implements Job {
    private Properties properties;

    public DwdLogTmpJob() {

    }

    @Override
    public void run() {
        // 一、Source
        //获取流量日志
        DataStream<String> jsonLogs = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.PAGE_AND_INFO_TOPIC, Common.DWD_SOURCE_CONSUMER_GROUP_ID);
        // 二、Transform
        //json映射为Bean
        SingleOutputStreamOperator<Object> beanLogs = jsonLogs
                .map(new RealTimeMapFunction(Common.FLOW_LOGS_JSON_TO_BEAN));
        //流量日志通过数盟过滤
        //流量日志融合基站流量日志
        //流量日志关联机型维表和地域维表
        //最后需要将Bean对象转化为字符串以应对写入Kafka的序列化
        SingleOutputStreamOperator<String> resLogs = beanLogs.map(bean -> JSON.toJSONString(bean));
        // 三、Sink
        resLogs.addSink(new FlinkKafkaProducer011<String>("", Common.DWD_LOG_TMP_TOPIC, new SimpleStringSchema()));
    }
}
