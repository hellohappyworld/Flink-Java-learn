package com.scallion.common;

import java.util.ArrayList;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function:常量类
 * origin ->
 */
public class Common {
    //Kafka
    public static final String KAFKA_BROKER = "10.66.224.128:9092";
    public static final String APP_NEWSAPP_TOPIC = "app_newsapp";
    public static final String APP_NEWSAPP_INFO_TOPIC = "app_newsapp_info";
    public static final String PAGE_AND_INFO_TOPIC = "app_newsapp_pageAndInfo_watermark";
    public static final String KAFKA_CONSUMER_GROUP_ID = "gaowj_ceshi_202105131505";
    //MySQL
    public static final String JDBCURL = "jdbc:mysql://10.21.7.108:3306?useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "x4rmkb8TuDiW85mU";
    public static final String DRIVERNAME = "com.mysql.jdbc.Driver";
    //Socket
    public static final String SOCKET_IP = "10.90.126.150";
    public static final int SOCKET_PORT = 9999;
    //点击opa(使用时候取非)
    public static final ArrayList<String> clickOpaTypes = new ArrayList<String>();

    static {
        clickOpaTypes.add("pageinfo");
        clickOpaTypes.add("adinfo");
        clickOpaTypes.add("btslist");
        clickOpaTypes.add("inloc");
    }

    //曝光opa
    public static final ArrayList<String> infoOpaTypes = new ArrayList<String>();

    static {
        infoOpaTypes.add("pageinfo");
        infoOpaTypes.add("adinfo");
    }
}
