package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-05-08.
 * function: 传感器温度类
 * origin -> https://www.cnblogs.com/shengyang17/p/12543524.html
 */
public class SensorReading {
    private String name; //传感器名称
    private long timestamp; //当前时间戳
    private double temperature; //当前温度值

    public SensorReading() {
    }

    public SensorReading(String name, long timestamp, double temperature) {
        this.name = name;
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
