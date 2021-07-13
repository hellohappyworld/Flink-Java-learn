package com.scallion.job;

import com.scallion.bean.OdsLogTmpBean;
import com.scallion.common.Common;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

/**
 * created by gaowj.
 * created on 2021-07-13.
 * function: 明细层Job
 */
public class OdsLogTmpJob implements Job {
    @Override
    public void run() {
        // 一、Source
        //获取流量日志
        DataStream<String> jsonLogs = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.PAGE_AND_INFO_TOPIC, Common.DWD_SOURCE_CONSUMER_GROUP_ID);
        // 二、Transform
        //json映射为Bean
        SingleOutputStreamOperator<OdsLogTmpBean> beanLogs = jsonLogs
                .map(new RealTimeMapFunction(Common.FLOW_LOGS_JSON_TO_BEAN))
                .map(bean -> (OdsLogTmpBean) bean);
        //关联维表数据 生成全量明细数据 log_tmp
        // 三、Sink
    }
}
