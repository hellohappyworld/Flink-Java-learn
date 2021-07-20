package com.scallion.transform;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * created by gaowj.
 * created on 2021-07-16.
 * function: 异步关联维表函数
 */
public class AsyncHBaseDimJoinFunction extends RichAsyncFunction<Object, Object> {
    private HBaseClient client;//HBase异步客户端
    private String joinType;//表的关联方式

    public AsyncHBaseDimJoinFunction(String joinType) {
        this.joinType = joinType;
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
            switch (joinType) {
                case Common.ACCOUNT_JOIN_ZMTCATEGORY: {
                    accountJoinZmtCategory(bean, resultFuture);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 账号数据关联自媒体类别维表
     *
     * @param bean
     * @param resultFuture
     */
    private void accountJoinZmtCategory(Object bean, ResultFuture<Object> resultFuture) {
        DimAccountBean dimAccountBean = (DimAccountBean) bean;
        ArrayList<GetRequest> getRequests = new ArrayList<>();
        getRequests.add(new GetRequest(Common.DIM_ZMT_CATEGORY_HBASE_TABLE,
                "aId", "dimFamily", "name"));
        getRequests.add(new GetRequest(Common.DIM_ZMT_CATEGORY_HBASE_TABLE,
                "aId", "dimFamily", "level"));
        getRequests.add(new GetRequest(Common.DIM_ZMT_CATEGORY_HBASE_TABLE,
                "aId", "dimFamily", "createTime"));
        getRequests.add(new GetRequest(Common.DIM_ZMT_CATEGORY_HBASE_TABLE,
                "aId", "dimFamily", "type"));

        Deferred<List<GetResultOrException>> listDeferred = client.get(getRequests);

        listDeferred.addCallback((Callback<String, List<GetResultOrException>>) callback -> {
            Iterator<GetResultOrException> callbackIterator = callback.iterator();
            while (callbackIterator.hasNext()) {
                GetResultOrException results = callbackIterator.next();
                ArrayList<KeyValue> cells = results.getCells();
                for (KeyValue kv : cells) {
                    String qualifier = new String(kv.qualifier());
                    String v = new String(kv.value());
                    if (StringUtils.isNotBlank(v)) {
                        switch (qualifier) {
                            case "name":
                                dimAccountBean.setField(v);
                                break;
                            case "level":
                                dimAccountBean.setFieldlevel(v);
                                break;
                            case "createTime":
                                dimAccountBean.setFieldcreatetime(v);
                                break;
                            case "type":
                                dimAccountBean.setFieldtype(v);
                                break;
                        }
                    }
                }
            }

            resultFuture.complete(Collections.singleton(dimAccountBean));

            return null;
        });
    }

}
