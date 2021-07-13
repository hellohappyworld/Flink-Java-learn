package com.scallion.job;

import com.scallion.bean.DimContentBean;
import com.scallion.common.Common;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

import java.util.ArrayList;

/**
 * created by gaowj.
 * created on 2021-07-13.
 * function: 内容维表Job
 */
public class DimContentJob implements Job {
    @Override
    public void run() {
        //1、Source
        //获取 文章 视频 专项 源数据，映射为Pojo
        ArrayList<String> topics = new ArrayList<>();
        topics.add(Common.UCMSSPECIALINFO);
        topics.add(Common.UCMSARTICLEINFO);
        topics.add(Common.UCMSSPECIALINFO);
        DataStream<String> jsonLogs = FlinkUtil.getKafkaStream(Common.KAFKA_DATACENTER_BROKER, topics, Common.DIM_SOURCE_CONSUMER_GROUP_ID);
        //2、Transform
        //清洗转换源数据Pojo
        SingleOutputStreamOperator<DimContentBean> beanLogs = jsonLogs
                .map(new RealTimeMapFunction(Common.CONTENTJSONTOBEAN))
                .map(bean -> (DimContentBean) bean);
        //关联 账号 员工 精品池 内容画像维表数据
        //3、Sink
        //维表数据写入HBase
    }
}
