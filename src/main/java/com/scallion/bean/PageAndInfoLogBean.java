package com.scallion.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * created by gaowj.
 * created on 2021-06-23.
 * function: 点击和曝光日志明细
 * origin ->
 */
public class PageAndInfoLogBean {
    @JSONField(name = "datatype")
    private String datatype;
    @JSONField(name = "ip")
    private String ip;
    @JSONField(name = "mos")
    private String mos;
    @JSONField(name = "softv")
    private String softv;
    @JSONField(name = "curpub")
    private String curpub;
    @JSONField(name = "userkey")
    private String userkey;
    @JSONField(name = "ua")
    private String ua;
    @JSONField(name = "net")
    private String net;
    @JSONField(name = "logintime")
    private String logintime;
    @JSONField(name = "etc")
    private String etc;
    @JSONField(name = "ct")
    private String ct;
    @JSONField(name = "opa")
    private String opa;
    @JSONField(name = "record")
    private String record;
    @JSONField(name = "ts_nginx")
    private String nginxTm;

    public PageAndInfoLogBean() {
    }

    public PageAndInfoLogBean(String datatype, String ip, String mos, String softv, String curpub, String userkey, String ua, String net, String logintime, String etc, String ct, String opa, String record, String nginxTm) {
        this.datatype = datatype;
        this.ip = ip;
        this.mos = mos;
        this.softv = softv;
        this.curpub = curpub;
        this.userkey = userkey;
        this.ua = ua;
        this.net = net;
        this.logintime = logintime;
        this.etc = etc;
        this.ct = ct;
        this.opa = opa;
        this.record = record;
        this.nginxTm = nginxTm;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMos() {
        return mos;
    }

    public void setMos(String mos) {
        this.mos = mos;
    }

    public String getSoftv() {
        return softv;
    }

    public void setSoftv(String softv) {
        this.softv = softv;
    }

    public String getCurpub() {
        return curpub;
    }

    public void setCurpub(String curpub) {
        this.curpub = curpub;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getOpa() {
        return opa;
    }

    public void setOpa(String opa) {
        this.opa = opa;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getNginxTm() {
        return nginxTm;
    }

    public void setNginxTm(String nginxTm) {
        this.nginxTm = nginxTm;
    }

    @Override
    public String toString() {
        return "PageAndInfoLogBean{" +
                "datatype='" + datatype + '\'' +
                ", ip='" + ip + '\'' +
                ", mos='" + mos + '\'' +
                ", softv='" + softv + '\'' +
                ", curpub='" + curpub + '\'' +
                ", userkey='" + userkey + '\'' +
                ", ua='" + ua + '\'' +
                ", net='" + net + '\'' +
                ", logintime='" + logintime + '\'' +
                ", etc='" + etc + '\'' +
                ", ct='" + ct + '\'' +
                ", opa='" + opa + '\'' +
                ", record='" + record + '\'' +
                ", nginxTm='" + nginxTm + '\'' +
                '}';
    }
}
