package com.scallion.job;

import com.scallion.bean.DimAccountBean;
import com.scallion.common.Common;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

/**
 * created by gaowj.
 * created on 2021-07-06.
 * function: 生成账号维表Job
 */
public class DimAccountJob implements Job {
    @Override
    public void run() {
        //Source
        //获取账号源日志
        DataStream<String> accountLogs = FlinkUtil.getKafkaStream(Common.KAFKA_DATACENTER_BROKER, Common.ACCOUNT_SOURCE_TOPIC, Common.DIM_SOURCE_CONSUMER_GROUP_ID);
        //Transform
        SingleOutputStreamOperator<DimAccountBean> accountBean = accountLogs
                .map(new RealTimeMapFunction(Common.ACCOUNTJSONTOBEAN))
                .map(bean -> (DimAccountBean) bean);
        //异步IO关联category.json
        //Sink
    }
}
