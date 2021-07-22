package com.scallion.job;

/**
 * created by gaowj.
 * created on 2021-07-22.
 * function: 用户维表Job
 */
public class DimUserJob implements Job {
    @Override
    public void run() {
        //一、Source
        //从dwdLogTmp中获取数据
        //二、Transform
        //清洗
        //过滤出新闻客户端用户gps明细数据
        //过滤出新闻客户端用户基站明细数据
        //关联applist维表(ods.newsapp_shumenglog)生成用户维表
        //三、Sink
        //新闻客户端用户gps明细数据写入Kafka
        //新闻客户端用户基站明细数据写入Kafka
        //用户维表写入HBase
    }
}
