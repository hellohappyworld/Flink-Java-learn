package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-03-10.
 * function:
 * origin ->
 */
public class WordWithCount {
    private String key;
    private long count;

    public WordWithCount() {
    }

    public WordWithCount(String key, long count) {
        this.key = key;
        this.count = count;
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

    @Override
    public String toString() {
        return "WordWithCount{" +
                "key='" + key + '\'' +
                ", count=" + count +
                '}';
    }
}
