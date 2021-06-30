package com.scallion.bean;

import org.apache.flink.api.java.tuple.Tuple2;

import java.util.HashMap;

/**
 * created by gaowj.
 * created on 2021-06-24.
 * function:实时概况每分钟计算结果对象
 * origin ->
 */
public class RealTimeOverviewResultBean {
    //窗口当前时间
    private String tm;
    //每分钟pv
    private int everyMinutePV;
    //每分钟uv
    private int everyMinuteUV;
    //每分钟分频道UV,PV Tuple2<Integer, Integer>第一位为UV值，第二位为PV值
    HashMap<String, Tuple2<Integer, Integer>> everyMinuteChUVAndPV;
    //每分钟分平台UV,PV Tuple2<Integer, Integer>第一位为UV值，第二位为PV值
    HashMap<String, Tuple2<Integer, Integer>> everyMinutePlatUVAndPV;
    //当日分频道累计UV
    HashMap<String, Integer> dayChUV;
    //当日分平台累计UV
    HashMap<String, Integer> dayPlatUV;

    public RealTimeOverviewResultBean() {
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public int getEveryMinutePV() {
        return everyMinutePV;
    }

    public void setEveryMinutePV(int everyMinutePV) {
        this.everyMinutePV = everyMinutePV;
    }

    public int getEveryMinuteUV() {
        return everyMinuteUV;
    }

    public void setEveryMinuteUV(int everyMinuteUV) {
        this.everyMinuteUV = everyMinuteUV;
    }

    public HashMap<String, Tuple2<Integer, Integer>> getEveryMinuteChUVAndPV() {
        return everyMinuteChUVAndPV;
    }

    public void setEveryMinuteChUVAndPV(HashMap<String, Tuple2<Integer, Integer>> everyMinuteChUVAndPV) {
        this.everyMinuteChUVAndPV = everyMinuteChUVAndPV;
    }

    public HashMap<String, Tuple2<Integer, Integer>> getEveryMinutePlatUVAndPV() {
        return everyMinutePlatUVAndPV;
    }

    public void setEveryMinutePlatUVAndPV(HashMap<String, Tuple2<Integer, Integer>> everyMinutePlatUVAndPV) {
        this.everyMinutePlatUVAndPV = everyMinutePlatUVAndPV;
    }

    public HashMap<String, Integer> getDayChUV() {
        return dayChUV;
    }

    public void setDayChUV(HashMap<String, Integer> dayChUV) {
        this.dayChUV = dayChUV;
    }

    public HashMap<String, Integer> getDayPlatUV() {
        return dayPlatUV;
    }

    public void setDayPlatUV(HashMap<String, Integer> dayPlatUV) {
        this.dayPlatUV = dayPlatUV;
    }

    @Override
    public String toString() {
        return "RealTimeOverviewResultBean{" +
                "tm='" + tm + '\'' +
                ", everyMinutePV=" + everyMinutePV +
                ", everyMinuteUV=" + everyMinuteUV +
                ", everyMinuteChUVAndPV=" + everyMinuteChUVAndPV +
                ", everyMinutePlatUVAndPV=" + everyMinutePlatUVAndPV +
                ", dayChUV=" + dayChUV +
                ", dayPlatUV=" + dayPlatUV +
                '}';
    }
}
