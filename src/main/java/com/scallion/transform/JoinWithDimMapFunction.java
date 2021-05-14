package com.scallion.transform;

import com.scallion.utils.TimeUtil;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * created by gaowj.
 * created on 2021-05-14.
 * function: 预加载|定时加载维表数据
 * origin ->
 */
public class JoinWithDimMapFunction extends RichMapFunction<String, String> {
    //选择合适的数据结构，用于将维表数据保存在内存中
    //也可以使用Google Guava CacheBuilder实现缓存
    private String dimRecord;

    //open方法在实际工作方法map方法工作之前被调用，因此适合工作前的配置，如对外部系统调用的配置，HBase,Redis,Mysql；
    //对于是迭代的部分，此方法将在每次迭代超步的开始处调用;
    //此处模拟每次定时从外部系统中获取维表数据，并缓存到内存中。
    @Override
    public void open(Configuration parameters) throws Exception {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dimRecord = TimeUtil.getTimestampToDate(System.currentTimeMillis()) + "时刻的维表数据";
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }

    /**
     * @param record 用户点击日志
     * @return
     * @throws Exception
     */
    @Override
    public String map(String record) throws Exception {
        String[] split = record.split("\t");
        String res = "userkey:" + split[5] + " opa:" + split[11] + " 关联" + dimRecord;
        return res;
    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}
