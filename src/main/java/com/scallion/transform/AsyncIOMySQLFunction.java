package com.scallion.transform;

import com.scallion.common.Common;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

/**
 * created by gaowj.
 * created on 2021-05-17.
 * function: 异步IO
 * origin ->
 */
public class AsyncIOMySQLFunction extends RichAsyncFunction<String, String> {
    private PreparedStatement ps;
    private Connection conn;

    @Override
    public void open(Configuration parameters) throws Exception {
        Class.forName(Common.DRIVERNAME);
        Connection conn = DriverManager.getConnection(Common.JDBCURL, Common.USERNAME, Common.PASSWORD);
        ps = conn.prepareStatement("select id,name,age,sex from tongji.rt_binlog_to_kudu where name='zhanghao'");
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }

    @Override
    public void asyncInvoke(String input, ResultFuture<String> resultFuture) throws Exception {
        ResultSet rs = ps.executeQuery();
        String sqlStr = "";
        if (rs.next()) {
            sqlStr = rs.getInt("id") +
                    rs.getString("name") +
                    rs.getInt("age") +
                    rs.getString("sex");
        }
        resultFuture.complete(Collections.singletonList(input.split("\t")[5] + " " + sqlStr));
    }
}
