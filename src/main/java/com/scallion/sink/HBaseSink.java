package com.scallion.sink;

import com.scallion.bean.DimAccountBean;
import com.scallion.bean.DimContentFeaturesBean;
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

/**
 * created by gaowj.
 * created on 2021-07-20.
 * function: 维表数据写入HBase
 * origin -> 参考自https://cloud.tencent.com/developer/article/1677260
 */
public class HBaseSink extends RichSinkFunction<Object> {
    private int maxSize; //批量写入HBase的最大条数
    private long delayTime; //最大延时时长
    private String beanType; //用于判断数据bean
    private String hbaseTable;//需要写入的HBase表

    private Connection connection; //HBase链接器
    private long lastInvokeTime; //上次批量插入HBase数据的时间
    private ArrayList<Put> puts = new ArrayList<>(); //存放插入HBase的数据

    public HBaseSink(int maxSize, long delayTime, String beanType, String hbaseTable) {
        this.maxSize = maxSize;
        this.delayTime = delayTime;
        this.beanType = beanType;
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
            switch (beanType) {
                case Common.DIM_CONTENT_FEATURES_TYPE: {
                    DimContentFeaturesBean dimContentFeaturesBean = (DimContentFeaturesBean) bean;
                    getDimContentFeaturesPut(dimContentFeaturesBean);
                    break;
                }
                case Common.DIM_ACCOUNT_TYPE: {
                    DimAccountBean dimAccountBean = (DimAccountBean) bean;
                    getDimAccountPut(dimAccountBean);
                    break;
                }
            }

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

    /**
     * 获取账号维表数据put
     *
     * @param bean
     */
    private void getDimAccountPut(DimAccountBean bean) {
        //同getDimContentFeaturesPut
    }

    /**
     * 获取内容维表数据put
     *
     * @param bean
     */
    private void getDimContentFeaturesPut(DimContentFeaturesBean bean) {
        String rowKey = bean.getDocid();
        Put put = new Put(rowKey.getBytes());//创建put对象并赋值rowKey
        //添加值：列蔟 列 值
        put.addColumn("dimFamily".getBytes(), "modifytime".getBytes(),
                bean.getModifytime().getBytes());
        put.addColumn("dimFamily".getBytes(), "scfeatures".getBytes(),
                bean.getScfeatures().getBytes());
        put.addColumn("dimFamily".getBytes(), "cfeatures".getBytes(),
                bean.getCfeatures().getBytes());
        put.addColumn("dimFamily".getBytes(), "otherfeatures".getBytes(),
                bean.getOtherfeatures().getBytes());
        put.addColumn("dimFamily".getBytes(), "importdate".getBytes(),
                bean.getImportdate().getBytes());
        put.addColumn("dimFamily".getBytes(), "otherState".getBytes(),
                bean.getOtherState().getBytes());
        put.addColumn("dimFamily".getBytes(), "expiretime".getBytes(),
                bean.getExpiretime().getBytes());
        put.addColumn("dimFamily".getBytes(), "searchpath".getBytes(),
                bean.getSearchpath().getBytes());
        put.addColumn("dimFamily".getBytes(), "classv".getBytes(),
                bean.getClassv().getBytes());
        //添加put对象到list集合
        puts.add(put);
    }
}































