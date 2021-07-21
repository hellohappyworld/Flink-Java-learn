package com.scallion.sink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scallion.common.Common;
import com.scallion.utils.HBaseUtil;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * created by gaowj.
 * created on 2021-07-20.
 * function: 维表数据写入HBase
 * origin -> 参考自https://cloud.tencent.com/developer/article/1677260
 */
public class HBaseSink extends RichSinkFunction<Object> {
    private int maxSize; //批量写入HBase的最大条数
    private long delayTime; //最大延时时长
    private String rowKey; //维表主键
    private String hbaseTable;//需要写入的HBase表

    private Connection connection; //HBase链接器
    private long lastInvokeTime; //上次批量插入HBase数据的时间
    private ArrayList<Put> puts = new ArrayList<>(); //存放插入HBase的数据

    public HBaseSink(int maxSize, long delayTime, String rowKey, String hbaseTable) {
        this.maxSize = maxSize;
        this.delayTime = delayTime;
        this.rowKey = rowKey;
        this.hbaseTable = hbaseTable;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        //获取全局配置文件
        ParameterTool params = (ParameterTool) getRuntimeContext().getExecutionConfig().getGlobalJobParameters();
        //创建一个HBase的链接
        connection = HBaseUtil.getConnection(params.getRequired("hbase.zookeeper.quorum"),
                params.getInt("hbase.zookeeper.property.clientPort"));
        //获取系统当前时间
        lastInvokeTime = System.currentTimeMillis();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public void invoke(Object bean, Context context) throws Exception {
        try {
            //将Bean对象转换为JsonObj，以便于提取各个字段数据
            JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(bean));
            Iterator<String> keySetIterator = jsonObj.keySet().iterator();
            //创建put对象并赋值rowkey
            Put put = new Put(jsonObj.getString(rowKey).getBytes());
            //添加值：列蔟 列 值
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                if (rowKey.equals(key))
                    continue;
                put.addColumn(Common.DIM_HBASE_TABLE_FAMLIY.getBytes(),
                        key.getBytes(),
                        jsonObj.getString(key).getBytes());
            }
            //添加Put对象到list集合
            puts.add(put);

            long currentTimeMillis = System.currentTimeMillis();
            //批量插入
            if ((puts.size() == maxSize || currentTimeMillis - lastInvokeTime >= delayTime) && !puts.isEmpty()) {
                Table table = connection.getTable(TableName.valueOf(hbaseTable));
                table.put(puts);//批量提交数据
                puts.clear();
                table.close();
                lastInvokeTime = currentTimeMillis;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}































