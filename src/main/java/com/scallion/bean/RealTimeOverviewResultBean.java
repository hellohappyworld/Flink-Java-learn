package com.scallion.bean;

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
    //当日累计UV
    private int dayUV;
    //当日分机型累计UV
    private HashMap<String, Integer> dayPhoneModelUV = new HashMap<String, Integer>();
    //当日分频道累计UV
    private HashMap<String, Integer> dayChannelUV = new HashMap<String, Integer>();
    //每分钟分机型UV
    private HashMap<String, Integer> everyMinuteChannelUV = new HashMap<String, Integer>();
    //每分钟分机型PV
    private HashMap<String, Integer> everyMinuteChannelPV = new HashMap<String, Integer>();

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

    public int getDayUV() {
        return dayUV;
    }

    public void setDayUV(int dayUV) {
        this.dayUV = dayUV;
    }

    public HashMap<String, Integer> getDayPhoneModelUV() {
        return dayPhoneModelUV;
    }

    public void setDayPhoneModelUV(HashMap<String, Integer> dayPhoneModelUV) {
        this.dayPhoneModelUV = dayPhoneModelUV;
    }

    public HashMap<String, Integer> getDayChannelUV() {
        return dayChannelUV;
    }

    public void setDayChannelUV(HashMap<String, Integer> dayChannelUV) {
        this.dayChannelUV = dayChannelUV;
    }

    public HashMap<String, Integer> getEveryMinuteChannelUV() {
        return everyMinuteChannelUV;
    }

    public void setEveryMinuteChannelUV(HashMap<String, Integer> everyMinuteChannelUV) {
        this.everyMinuteChannelUV = everyMinuteChannelUV;
    }

    public HashMap<String, Integer> getEveryMinuteChannelPV() {
        return everyMinuteChannelPV;
    }

    public void setEveryMinuteChannelPV(HashMap<String, Integer> everyMinuteChannelPV) {
        this.everyMinuteChannelPV = everyMinuteChannelPV;
    }

    @Override
    public String toString() {
        return "RealTimeOverviewResultBean{" +
                "tm='" + tm + '\'' +
                ", everyMinutePV=" + everyMinutePV +
                ", everyMinuteUV=" + everyMinuteUV +
                ", dayUV=" + dayUV +
                ", dayPhoneModelUV=" + dayPhoneModelUV +
                ", dayChannelUV=" + dayChannelUV +
                ", everyMinuteChannelUV=" + everyMinuteChannelUV +
                ", everyMinuteChannelPV=" + everyMinuteChannelPV +
                '}';
    }
}
