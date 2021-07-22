package com.scallion.job;

import com.scallion.common.Common;
import com.scallion.sink.HBaseSink;
import com.scallion.transform.AsyncHBaseDimJoinFunction;
import com.scallion.transform.RealTimeMapFunction;
import com.scallion.utils.FlinkUtil;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * created by gaowj.
 * created on 2021-07-13.
 * function: 内容维表Job
 */
public class DimContentJob implements Job {
    private String rowKeyCol; //与精品池维表,内容画像维表关联时的主键列名
    private HashMap<String, HashSet<String>> joinTables;//与精品池维表,内容画像维表关联时需要关联的表名及其字段
    private HashMap<String, String> colAndResCol;//与精品池维表,内容画像维表关联时用 map的key为维表列名，value为流量bean的列名

    private String staffRowKeyCol;//与员工维表关联时的主键列名
    private HashMap<String, HashSet<String>> staffJoinTables;
    private HashMap<String, String> staffColAndResCol;

    private String accountRowKeyCol;//与账号维表关联时用的主键列名
    private HashMap<String, HashSet<String>> accountJoinTables;
    private HashMap<String, String> accountColAndResCol;

    public DimContentJob() {
        //精品池维表和内容画像维表
        rowKeyCol = Common.DIM_CONTENT_ROW_KEY;
        joinTables = new HashMap<String, HashSet<String>>();
        HashSet<String> jpPoolCols = new HashSet<>();
        jpPoolCols.add("ch");
        jpPoolCols.add("userName");
        joinTables.put(Common.DIM_CONTENT_FEATURES_HBASE_TABLE, jpPoolCols);
        HashSet<String> contentFeaturesCols = new HashSet<>();
        contentFeaturesCols.add("cfeatures");
        contentFeaturesCols.add("scfeatures");
        contentFeaturesCols.add("importDate");
        contentFeaturesCols.add("otherState");
        contentFeaturesCols.add("expireTime");
        contentFeaturesCols.add("searchPath");
        contentFeaturesCols.add("classV");
        joinTables.put(Common.DIM_JPPOOL_HBASE_TABLE, contentFeaturesCols);
        colAndResCol = new HashMap<String, String>();
        colAndResCol.put("ch", "jppoolCh");
        colAndResCol.put("userName", "jppoolUserName");
        colAndResCol.put("cfeatures", "c");
        colAndResCol.put("scfeatures", "sc");
        colAndResCol.put("importDate", "importDate");
        colAndResCol.put("otherState", "otherState");
        colAndResCol.put("expireTime", "expireTime");
        colAndResCol.put("searchPath", "searchPath");
        colAndResCol.put("classV", "classV");
        //员工维表
        staffRowKeyCol = Common.DIM_CONTENT_JOIN_STAFF_ROW_KEY;
        staffJoinTables = new HashMap<String, HashSet<String>>();
        HashSet<String> staffCols = new HashSet<>();
        staffCols.add("name");
        staffCols.add("dept");
        staffJoinTables.put(Common.DIM_STAFF_HBASE_TABLE, staffCols);
        staffColAndResCol = new HashMap<String, String>();
        staffColAndResCol.put("name", "creator");
        staffColAndResCol.put("dept", "dept");
        //账号维表
        accountRowKeyCol = Common.DIM_CONTENT_JOIN_ACCOUNT_ROW_KEY;
        accountJoinTables = new HashMap<String, HashSet<String>>();
        HashSet<String> accountCols = new HashSet<>();
        accountCols.add("weMediaName");
        accountCols.add("accountType");
        accountCols.add("level");
        accountCols.add("fhtId");
        accountCols.add("applyTime");
        accountCols.add("online");
        accountCols.add("fhhcopyright");
        accountCols.add("copyright");
        accountJoinTables.put(Common.DIM_ACCOUNT_HBASE_TABLE, accountCols);
        accountColAndResCol = new HashMap<String, String>();
        accountColAndResCol.put("weMediaName", "src");
        accountColAndResCol.put("accountType", "tsrc");
        accountColAndResCol.put("level", "level");
        accountColAndResCol.put("fhtId", "fhtId");
        accountColAndResCol.put("applyTime", "applyTime");
    }

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
        SingleOutputStreamOperator<Object> beanLogs = jsonLogs
                .map(new RealTimeMapFunction(Common.CONTENTJSONTOBEAN));
        //关联精品池维表和内容画像维表
        SingleOutputStreamOperator<Object> beanLogsJoin1 = AsyncDataStream
                .unorderedWait(beanLogs,
                        new AsyncHBaseDimJoinFunction(rowKeyCol, joinTables, colAndResCol),
                        1000, TimeUnit.MILLISECONDS, 100);
        //关联员工表
        SingleOutputStreamOperator<Object> beanLogsJoin2 = AsyncDataStream.unorderedWait(beanLogsJoin1,
                new AsyncHBaseDimJoinFunction(staffRowKeyCol, staffJoinTables, staffColAndResCol),
                1000, TimeUnit.MILLISECONDS, 100);
        //关联账号维表
        SingleOutputStreamOperator<Object> resBean = AsyncDataStream.unorderedWait(beanLogsJoin2,
                new AsyncHBaseDimJoinFunction(accountRowKeyCol, accountJoinTables, accountColAndResCol),
                1000, TimeUnit.MILLISECONDS, 100);
        //3、Sink
        //维表数据写入HBase
        resBean.addSink(new HBaseSink(5, 60000,
                Common.DIM_CONTENT_ROW_KEY, Common.DIM_CONTENT_HBASE_TABLE));
    }
}
