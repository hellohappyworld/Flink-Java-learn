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

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * created by gaowj.
 * created on 2021-07-06.
 * function: 生成账号维表Job
 */
public class DimAccountJob implements Job {
    private String rowKeyCol; //主键列名
    private HashMap<String, HashSet<String>> joinTables;//需要关联的表名及其字段
    private HashMap<String, String> colAndResCol;//map的key为维表列名，value为流量bean的列名

    public DimAccountJob() {
        rowKeyCol = Common.DIM_ACCOUNT_ROW_KEY;
        HashSet<String> tableCols = new HashSet<>();//需要关联的表列名
        tableCols.add("name");
        tableCols.add("level");
        tableCols.add("createTime");
        tableCols.add("type");
        joinTables = new HashMap<String, HashSet<String>>();
        joinTables.put(Common.DIM_ZMT_CATEGORY_HBASE_TABLE, tableCols);
        colAndResCol = new HashMap<String, String>();
        colAndResCol.put("name", "field");
        colAndResCol.put("level", "fieldlevel");
        colAndResCol.put("createTime", "fieldcreatetime");
        colAndResCol.put("type", "fieldtype");
    }

    @Override
    public void run() {
        //Source
        //获取账号源日志
        DataStream<String> accountLogs = FlinkUtil.getKafkaStream(Common.KAFKA_DATACENTER_BROKER, Common.ACCOUNT_SOURCE_TOPIC, Common.DIM_SOURCE_CONSUMER_GROUP_ID);
        //Transform
        SingleOutputStreamOperator<Object> accountBean = accountLogs
                .map(new RealTimeMapFunction(Common.ACCOUNTJSONTOBEAN));
        //异步IO关联category.json
        SingleOutputStreamOperator<Object> joinBean = AsyncDataStream.unorderedWait(accountBean,
                new AsyncHBaseDimJoinFunction(rowKeyCol, joinTables, colAndResCol),
                1000, TimeUnit.MILLISECONDS, 100);
        //Sink
        joinBean.addSink(new HBaseSink(50, 60000, Common.DIM_ACCOUNT_ROW_KEY, Common.DIM_ACCOUNT_HBASE_TABLE));
    }
}
