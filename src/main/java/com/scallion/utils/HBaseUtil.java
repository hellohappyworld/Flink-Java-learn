package com.scallion.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import org.apache.hadoop.hbase.client.Connection;

/**
 * created by gaowj.
 * created on 2021-07-20.
 * function: HBase工具类
 * origin -> https://cloud.tencent.com/developer/article/1677260
 */
public class HBaseUtil {
    /**
     * 获取HBase链接
     *
     * @param zkQuorum zookeeper地址，多个地址使用逗号隔开
     * @param port     zookeeper端口号
     * @return
     */
    public static Connection getConnection(String zkQuorum, int port) {
        try {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", zkQuorum);
            conf.set("hbase.zookeeper.property.clientPort", String.valueOf(port));

            Connection connection = ConnectionFactory.createConnection(conf);
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
