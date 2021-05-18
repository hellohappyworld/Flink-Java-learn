package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-05-18.
 * function: 累加器
 * origin ->
 */
public class CountAccumulator {
    private String userKey;
    private int count;
    private String startTm;
    private String endTm;

    public CountAccumulator() {
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getStartTm() {
        return startTm;
    }

    public void setStartTm(String startTm) {
        this.startTm = startTm;
    }

    public String getEndTm() {
        return endTm;
    }

    public void setEndTm(String endTm) {
        this.endTm = endTm;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountAccumulator{" +
                "userKey='" + userKey + '\'' +
                ", count=" + count +
                ", startTm='" + startTm + '\'' +
                ", endTm='" + endTm + '\'' +
                '}';
    }

}
