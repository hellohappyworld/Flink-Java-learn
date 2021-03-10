package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function:
 * origin ->
 */
public class CountWithTimestampState {
    private String key;
    private long count;
    private long lastModified;

    public CountWithTimestampState() {
    }

    public CountWithTimestampState(String key, long count, long lastModified) {
        this.key = key;
        this.count = count;
        this.lastModified = lastModified;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "CountWithTimestampState{" +
                "key='" + key + '\'' +
                ", count=" + count +
                ", lastModified=" + lastModified +
                '}';
    }
}
