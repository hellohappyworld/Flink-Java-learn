package com.scallion.job;

import com.scallion.common.Common;
import com.scallion.transform.JoinWithDimMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

/**
 * created by gaowj.
 * created on 2021-05-14.
 * function: 维表Join
 * origin ->
 */
public class JoinWithDimJob implements Job {
    @Override
    public void run() {
        /**
         * Source
         */
        //点击日志
        DataStream<String> clickStream = FlinkUtil.getKafkaStream(Common.KAFKA_BROKER, Common.APP_NEWSAPP_TOPIC, Common.KAFKA_CONSUMER_GROUP_ID);
        /**
         * Transform
         */
        //1、预加载维表
        SingleOutputStreamOperator<String> joinWithDimStream = clickStream.map(new JoinWithDimMapFunction());
        //2、热存储维表
        //（1）、使用cache来减轻访问压力
        //（2）、使用异步IO来提高访问吞吐量
        //3、广播维表
        //4、临时表函数Join
        /**
         * Sink
         */
        joinWithDimStream.print();
    }
}
