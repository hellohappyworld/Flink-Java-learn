package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-05-13.
 * function: 点击日志信息类
 * origin ->
 */
public class AnalyticsAccessLogRecord {
    private int merchandiseId; //商品ID

    public AnalyticsAccessLogRecord() {
    }

    public AnalyticsAccessLogRecord(int merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public int getMerchandiseId() {
//        System.out.println("click:" + merchandiseId);
        return merchandiseId;
    }

    public void setMerchandiseId(int merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    @Override
    public String toString() {
        return "AnalyticsAccessLogRecord{" +
                "merchandiseId='" + merchandiseId + '\'' +
                '}';
    }
}
