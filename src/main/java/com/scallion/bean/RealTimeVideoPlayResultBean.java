package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-07-01.
 * function: 实时播放结果数据对象
 */
public class RealTimeVideoPlayResultBean {
    //播放量
    int playCount = 0;
    //播放成功量
    int playSuccessCount = 0;
    //视频曝光量
    int videoInfoCount = 0;

    public RealTimeVideoPlayResultBean() {
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getPlaySuccessCount() {
        return playSuccessCount;
    }

    public void setPlaySuccessCount(int playSuccessCount) {
        this.playSuccessCount = playSuccessCount;
    }

    public int getVideoInfoCount() {
        return videoInfoCount;
    }

    public void setVideoInfoCount(int videoInfoCount) {
        this.videoInfoCount = videoInfoCount;
    }

    @Override
    public String toString() {
        return "RealTimeVideoPlayResultBean{" +
                "playCount=" + playCount +
                ", playSuccessCount=" + playSuccessCount +
                ", videoInfoCount=" + videoInfoCount +
                '}';
    }
}
