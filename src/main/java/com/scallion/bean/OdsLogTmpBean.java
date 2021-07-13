package com.scallion.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * created by gaowj.
 * created on 2021-07-13.
 * function: ods层--流量日志轻度ETL
 */
public class OdsLogTmpBean {
    @JSONField(name = "datatype")
    private String dataType;
    @JSONField(name = "ip")
    private String ip;
    @JSONField(name = "plat")
    private String plat;
    @JSONField(name = "platV")
    private String platV;
    @JSONField(name = "softv")
    private String softV;
    @JSONField(name = "curpub")
    private String curpub;
    @JSONField(name = "userkey")
    private String userKey;
    @JSONField(name = "device")
    private String device;
    @JSONField(name = "brand")
    private String brand;
    @JSONField(name = "net")
    private String net;
    @JSONField(name = "logintime")
    private String loginTime;
    @JSONField(name = "isUpdate")
    private String isUpdate;
    @JSONField(name = "tm")
    private String tm;
    @JSONField(name = "sig2")
    private String sig2;
    @JSONField(name = "md5")
    private String md5;
    @JSONField(name = "sha1")
    private String sha1;
    @JSONField(name = "loginId")
    private String loginId;
    @JSONField(name = "uName")
    private String uName;
    @JSONField(name = "idfa")
    private String idfa;
    @JSONField(name = "head")
    private String head;
    @JSONField(name = "sdkver")
    private String sdkver;
    @JSONField(name = "isup")
    private String isup;
    @JSONField(name = "ct")
    private String ct;
    @JSONField(name = "record")
    private String record;
    @JSONField(name = "subBrand")
    private String subBrand;
    @JSONField(name = "price")
    private String price;
    @JSONField(name = "priceRange")
    private String priceRange;
    @JSONField(name = "comePub")
    private String comePub;
    @JSONField(name = "newDt")
    private String newDt;
    @JSONField(name = "country")
    private String country;
    @JSONField(name = "region")
    private String region;
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "operator")
    private String operator;
    @JSONField(name = "opa")
    private String opa;
    @JSONField(name = "visitTag")
    private String visitTag;
    @JSONField(name = "firstInType")
    private String firstInType;//新用户第一次来访打开方式
    @JSONField(name = "firstInKind")
    private String firstInKind;//新用户第一次来访打开方式为第三方时发送，根据第三方拉起数据区分来源类别。需要发送类别名称id及app名称id，两部分名称id用’|’连接，名称id自定义，均小写，如没有分类发other
    @JSONField(name = "userKv")
    private String userKv;//这是个kv类型的json数据，表示其他小众属性信息
    @JSONField(name = "ts_nginx")
    private String tsNginx;//事件时间

    public OdsLogTmpBean() {
        this.dataType = "#";
        this.ip = "#";
        this.plat = "#";
        this.platV = "#";
        this.softV = "#";
        this.curpub = "#";
        this.userKey = "#";
        this.device = "#";
        this.brand = "#";
        this.net = "#";
        this.loginTime = "#";
        this.isUpdate = "#";
        this.tm = "#";
        this.sig2 = "#";
        this.md5 = "#";
        this.sha1 = "#";
        this.loginId = "#";
        this.uName = "#";
        this.idfa = "#";
        this.head = "#";
        this.sdkver = "#";
        this.isup = "#";
        this.ct = "#";
        this.record = "#";
        this.subBrand = "#";
        this.price = "#";
        this.priceRange = "#";
        this.comePub = "#";
        this.newDt = "#";
        this.country = "#";
        this.region = "#";
        this.city = "#";
        this.operator = "#";
        this.opa = "#";
        this.visitTag = "#";
        this.firstInType = "#";
        this.firstInKind = "#";
        this.userKv = "#";
        this.tsNginx = "#";
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getPlatV() {
        return platV;
    }

    public void setPlatV(String platV) {
        this.platV = platV;
    }

    public String getSoftV() {
        return softV;
    }

    public void setSoftV(String softV) {
        this.softV = softV;
    }

    public String getCurpub() {
        return curpub;
    }

    public void setCurpub(String curpub) {
        this.curpub = curpub;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getSig2() {
        return sig2;
    }

    public void setSig2(String sig2) {
        this.sig2 = sig2;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getSdkver() {
        return sdkver;
    }

    public void setSdkver(String sdkver) {
        this.sdkver = sdkver;
    }

    public String getIsup() {
        return isup;
    }

    public void setIsup(String isup) {
        this.isup = isup;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getComePub() {
        return comePub;
    }

    public void setComePub(String comePub) {
        this.comePub = comePub;
    }

    public String getNewDt() {
        return newDt;
    }

    public void setNewDt(String newDt) {
        this.newDt = newDt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOpa() {
        return opa;
    }

    public void setOpa(String opa) {
        this.opa = opa;
    }

    public String getVisitTag() {
        return visitTag;
    }

    public void setVisitTag(String visitTag) {
        this.visitTag = visitTag;
    }

    public String getFirstInType() {
        return firstInType;
    }

    public void setFirstInType(String firstInType) {
        this.firstInType = firstInType;
    }

    public String getFirstInKind() {
        return firstInKind;
    }

    public void setFirstInKind(String firstInKind) {
        this.firstInKind = firstInKind;
    }

    public String getUserKv() {
        return userKv;
    }

    public void setUserKv(String userKv) {
        this.userKv = userKv;
    }

    public String getTsNginx() {
        return tsNginx;
    }

    public void setTsNginx(String tsNginx) {
        this.tsNginx = tsNginx;
    }

    @Override
    public String toString() {
        return "OdsLogTmpBean{" +
                "dataType='" + dataType + '\'' +
                ", ip='" + ip + '\'' +
                ", plat='" + plat + '\'' +
                ", platV='" + platV + '\'' +
                ", softV='" + softV + '\'' +
                ", curpub='" + curpub + '\'' +
                ", userKey='" + userKey + '\'' +
                ", device='" + device + '\'' +
                ", brand='" + brand + '\'' +
                ", net='" + net + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", isUpdate='" + isUpdate + '\'' +
                ", tm='" + tm + '\'' +
                ", sig2='" + sig2 + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", loginId='" + loginId + '\'' +
                ", uName='" + uName + '\'' +
                ", idfa='" + idfa + '\'' +
                ", head='" + head + '\'' +
                ", sdkver='" + sdkver + '\'' +
                ", isup='" + isup + '\'' +
                ", ct='" + ct + '\'' +
                ", record='" + record + '\'' +
                ", subBrand='" + subBrand + '\'' +
                ", price='" + price + '\'' +
                ", priceRange='" + priceRange + '\'' +
                ", comePub='" + comePub + '\'' +
                ", newDt='" + newDt + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", operator='" + operator + '\'' +
                ", opa='" + opa + '\'' +
                ", visitTag='" + visitTag + '\'' +
                ", firstInType='" + firstInType + '\'' +
                ", firstInKind='" + firstInKind + '\'' +
                ", userKv='" + userKv + '\'' +
                ", tsNginx='" + tsNginx + '\'' +
                '}';
    }
}
