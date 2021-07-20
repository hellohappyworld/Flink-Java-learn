package com.scallion.job;

import com.scallion.bean.DimContentFeaturesBean;
import com.scallion.common.Common;
import com.scallion.sink.HBaseSink;
import com.scallion.transform.DimContentFeaturesMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

/**
 * created by gaowj.
 * created on 2021-07-08.
 * function:内容画像维表Job
 */
public class DimContentFeaturesJob implements Job {
    @Override
    public void run() {
        //Source
        DataStream<String> source = FlinkUtil.getKafkaStream(Common.KAFKA_TC_BROKER, Common.CONTENT_FEATURES_TOPIC, Common.DIM_SOURCE_CONSUMER_GROUP_ID);
        //Transform
        SingleOutputStreamOperator<Object> resultBean = source.map(new DimContentFeaturesMapFunction())
                .map(bean -> (Object) bean);
        //Sink
        resultBean.addSink(new HBaseSink(50, 60000, Common.DIM_CONTENT_FEATURES_TYPE, Common.DIM_CONTENT_FEATURES_HBASE_TABLE));
    }
}
