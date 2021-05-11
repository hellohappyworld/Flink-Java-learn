package com.scallion.transform;

import com.scallion.bean.SensorReading;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-05-08.
 * function: 检测传感器温度
 * origin -> https://www.cnblogs.com/shengyang17/p/12543524.html
 */
public class TempIncreAlert extends KeyedProcessFunction<String, SensorReading, String> {
    //温度连续上升，跟上一条数据做对比，把上一条数据保存成当前状态
    //定义一个状态，保存上一个数据的温度值
    private ValueState<Double> lastTemp;
    //定义一个状态，用来保存定时器的时间戳
    private ValueState<Long> currentTimer;

    //首先获得由这个处理函数(process function)维护的状态
    @Override
    public void open(Configuration parameters) throws Exception {
        lastTemp = getRuntimeContext().getState(new ValueStateDescriptor<Double>("lastTemp", Double.class));
        currentTimer = getRuntimeContext().getState(new ValueStateDescriptor<Long>("currentTimer", Long.class));
    }

    //对于在输入流中接收到的每个事件，此函数就会被调用以处理该函数
    @Override
    public void processElement(SensorReading sensorReading, Context context, Collector<String> collector) throws Exception {
        //获取上一个温度值
        Double preTemp = lastTemp.value();
        //更新温度值
        lastTemp.update(sensorReading.getTemperature());
        //从当前定时器取出时间戳，默认值为0
        Long currentTimerTs = currentTimer.value();
        if (sensorReading.getTemperature() > preTemp && currentTimerTs == 0) {
            //如果温度上升且没有设置定时器，则注册定时器
            long timerTs = context.timerService().currentProcessingTime() + 10000L; //当前时间+10s
            context.timerService().registerProcessingTimeTimer(timerTs); //注册定时器
            currentTimer.update(timerTs);
        } else if (preTemp > sensorReading.getTemperature() || preTemp == 0.0) {
            //如果温度下降，或是第一条数据(定时器默认为0)，删除定时器并清空状态
            context.timerService().deleteProcessingTimeTimer(currentTimerTs); //删除定时器
            currentTimer.clear(); //把对应的转态清空，防止撑爆内存
        }
    }

    //定义回调函数需要做的事情
    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
        //输出报警信息
        //此处的ctx.getCurrentKey()即为传感器名称
        out.collect(ctx.getCurrentKey() + "温度持续上升");
        currentTimer.clear(); //清空状态
    }
}
