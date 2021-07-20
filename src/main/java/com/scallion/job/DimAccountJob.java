package com.scallion.job;

import com.scallion.bean.DimAccountBean;
import com.scallion.common.Common;
import com.scallion.sink.HBaseSink;
import com.scallion.transform.AsyncHBaseDimJoinFunction;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

import java.util.concurrent.TimeUnit;

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
        SingleOutputStreamOperator<Object> accountBean = accountLogs
                .map(new RealTimeMapFunction(Common.ACCOUNTJSONTOBEAN));
        //异步IO关联category.json
        SingleOutputStreamOperator<Object> joinBean = AsyncDataStream.unorderedWait(accountBean, new AsyncHBaseDimJoinFunction(Common.ACCOUNT_JOIN_ZMTCATEGORY),
                1000, TimeUnit.MILLISECONDS, 100);
        //Sink
        joinBean.addSink(new HBaseSink(50, 60000, Common.DIM_ACCOUNT_TYPE, Common.DIM_ACCOUNT_HBASE_TABLE));
    }
}
