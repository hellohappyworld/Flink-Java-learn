package com.scallion.transform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scallion.bean.DimAccountBean;
import com.scallion.common.Common;
import com.stumbleupon.async.Callback;
import com.stumbleupon.async.Deferred;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.hbase.async.GetRequest;
import org.hbase.async.GetResultOrException;
import org.hbase.async.HBaseClient;
import org.hbase.async.KeyValue;
import scala.math.Ordering;

import java.util.*;

/**
 * created by gaowj.
 * created on 2021-07-16.
 * function: 异步关联维表函数
 */
public class AsyncHBaseDimJoinFunction extends RichAsyncFunction<Object, Object> {
    private HBaseClient client;//HBase异步客户端
    private String rowKeyCol; //主键列名
    private HashMap<String, HashSet<String>> joinTables;//需要关联的表名及其字段
    private HashMap<String, String> colAndResCol;//map的key为维表列名，value为流量bean的列名

    public AsyncHBaseDimJoinFunction(String rowKeyCol, HashMap<String, HashSet<String>> joinTables, HashMap<String, String> colAndResCol) {
        this.rowKeyCol = rowKeyCol;
        this.joinTables = joinTables;
        this.colAndResCol = colAndResCol;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        //获取全局配置文件
        ParameterTool params = (ParameterTool) getRuntimeContext().getExecutionConfig().getGlobalJobParameters();
        //获取HBase连接
        client = new HBaseClient(params.getRequired("hbase.zookeeper.quorum"),
                params.getRequired("hbase.zookeeper.property.clientPort"));
    }

    @Override
    public void asyncInvoke(Object bean, ResultFuture<Object> resultFuture) throws Exception {
        try {
            //流量日志
            JSONObject beanJsonObj = JSON.parseObject(JSON.toJSONString(bean));
            String rowKey = beanJsonObj.getString(rowKeyCol);//主键列值
            ArrayList<GetRequest> getRequests = new ArrayList<>();
            //需要join的维表名
            Iterator<String> tables = joinTables.keySet().iterator();
            while (tables.hasNext()) {
                String table = tables.next();
                HashSet<String> cols = joinTables.get(table);//需要关联的列名
                Iterator<String> colsIterator = cols.iterator();
                while (colsIterator.hasNext()) {
                    String col = colsIterator.next();
                    getRequests.add(new GetRequest(table, rowKey,
                            Common.DIM_HBASE_TABLE_FAMLIY,
                            col));
                }
            }
            Deferred<List<GetResultOrException>> listDeferred = client.get(getRequests);
            listDeferred.addCallbacks(new Callback<Object, List<GetResultOrException>>() {
                @Override
                public Object call(List<GetResultOrException> callBack) throws Exception {
                    if (callBack != null && !callBack.isEmpty()) {
                        Iterator<GetResultOrException> callBackIterator = callBack.iterator();
                        while (callBackIterator.hasNext()) {
                            GetResultOrException results = callBackIterator.next();
                            ArrayList<KeyValue> cells = results.getCells();
                            for (KeyValue kv : cells) {
                                String qualifier = new String(kv.qualifier());//维表列名
                                String v = new String(kv.value());
                                if (StringUtils.isNotBlank(v)) {
                                    String resCol = colAndResCol.get(qualifier);//流量日志bean的列名
                                    beanJsonObj.put(resCol, v);
                                }
                            }
                        }
                    } else {
                        //收集关联后的结果数据，或者未关联的数据
                        resultFuture.complete(Collections.singleton(beanJsonObj));
                    }
                    return null;
                }
            }, new Callback<Object, Object>() {
                @Override
                public Object call(Object o) throws Exception {
                    //收集关联时候出现异常的原始bean数据
                    resultFuture.complete(Collections.singleton(beanJsonObj));
                    return null;
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
