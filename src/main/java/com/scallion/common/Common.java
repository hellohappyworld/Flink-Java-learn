package com.scallion.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function:常量类
 * origin ->
 */
public class Common {
    //Kafka
    public static final String KAFKA_BROKER = "10.66.224.128:9092";
    public static final String KAFKA_TC_BROKER = "10.64.225.13:9092";
    public static final String KAFKA_DATACENTER_BROKER = "10.66.224.160:9092";
    public static final String ACCOUNT_SOURCE_TOPIC = "zmtAccountInfo";
    public static final String APP_NEWSAPP_TOPIC = "app_newsapp";
    public static final String APP_NEWSAPP_INFO_TOPIC = "app_newsapp_info";
    public static final String PAGE_AND_INFO_TOPIC = "app_newsapp_pageAndInfo_watermark";
    public static final String CONTENT_FEATURES_TOPIC = "tc_all_analysis_data";
    public static final String UCMSARTICLEINFO = "ucmsArticleInfo";
    public static final String UCMSVIDEOINFO = "ucmsVideoInfo";
    public static final String UCMSSPECIALINFO = "ucmsSpecialInfo";
    //维表源数据消费者组
    public static final String DIM_SOURCE_CONSUMER_GROUP_ID = "gaowj_ceshi_dim_202107081110";
    //实时概况消费者组 RealTimeOverview
    public static final String KAFKA_CONSUMER_GROUP_ID = "gaowj_ceshi_realTimeOverview_202105131505";
    //实时播放消费者组
    public static final String VIDEO_PLAY_KAFKA_CONSUMER_GROUP_ID = "gaowj_ceshi_videoPlay_202107011519";
    //实时点击排行消费者组
    public static final String CLICK_RANK_KAFKA_CONSUMER_GROUP_ID = "gaowj_ceshi_clickRank_202107021556";
    //MySQL
    public static final String JDBCURL = "jdbc:mysql://10.21.7.108:3306?useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "x4rmkb8TuDiW85mU";
    public static final String DRIVERNAME = "com.mysql.jdbc.Driver";
    //ORACLE
    public static final String ORACLEJDBCURL = "oracle.jdbc.driver.OracleDriver";
    //指定连接位置jdbc:oracle:thin:  ip地址 : 端口号 : <数据库名>
    public static final String ORACLEURL = "jdbc:oracle:thin:@10.90.19.1:1521:orcl";
    public static final String ORACLEUSERNAME = "app";
    public static final String ORACLEPASSWORD = "";
    //Socket
    public static final String SOCKET_IP = "10.90.126.150";
    public static final int SOCKET_PORT = 9999;
    //所有的opa
    public static final HashMap<String, String> OPATYPES = new HashMap<>();

    static {
        OPATYPES.put("v", "v");
        OPATYPES.put("pageinfo", "pageinfo");
        OPATYPES.put("page", "page");
        OPATYPES.put("action", "action");
        OPATYPES.put("ts", "ts");
    }

    //点击opa(使用时候取非)
    public static final ArrayList<String> CLICKOPATYPES = new ArrayList<String>();

    static {
        CLICKOPATYPES.add("pageinfo");
        CLICKOPATYPES.add("adinfo");
        CLICKOPATYPES.add("btslist");
        CLICKOPATYPES.add("inloc");
    }

    //曝光opa
    public static final ArrayList<String> INFOOPATYPES = new ArrayList<String>();

    static {
        INFOOPATYPES.add("pageinfo");
        INFOOPATYPES.add("adinfo");
    }

    //需要过滤的发布版本号
    public static final HashSet<String> CURPUB = new HashSet<String>();

    static {
        CURPUB.add("7932");
        CURPUB.add("7927");
        CURPUB.add("7925");
        CURPUB.add("7928");
        CURPUB.add("7933");
        CURPUB.add("7924");
        CURPUB.add("7931");
        CURPUB.add("7930");
        CURPUB.add("7926");
        CURPUB.add("2558");
        CURPUB.add("7797");
        CURPUB.add("2443");
        CURPUB.add("8053");
        CURPUB.add("8052");
        CURPUB.add("8050");
        CURPUB.add("8054");
        CURPUB.add("1029");
        CURPUB.add("2040");
        CURPUB.add("8046");
        CURPUB.add("8047");
        CURPUB.add("8048");
        CURPUB.add("8049");
        CURPUB.add("8051");
        CURPUB.add("8045");
        CURPUB.add("8148");
        CURPUB.add("8169");
        CURPUB.add("2638");
        CURPUB.add("2635");
        CURPUB.add("2634");
        CURPUB.add("2637");
        CURPUB.add("2636");
        CURPUB.add("9023");
        CURPUB.add("8104");
        CURPUB.add("7944");
        CURPUB.add("7945");
        CURPUB.add("7946");
        CURPUB.add("7940");
        CURPUB.add("7941");
        CURPUB.add("7942");
        CURPUB.add("7943");
        CURPUB.add("7937");
        CURPUB.add("7939");
        CURPUB.add("7938");
        CURPUB.add("2616");
        CURPUB.add("2615");
        CURPUB.add("7856");
        CURPUB.add("7854");
        CURPUB.add("7855");
        CURPUB.add("8134");
        CURPUB.add("8136");
        CURPUB.add("8137");
        CURPUB.add("8130");
        CURPUB.add("8132");
        CURPUB.add("8129");
        CURPUB.add("8127");
        CURPUB.add("8125");
        CURPUB.add("8123");
        CURPUB.add("8122");
        CURPUB.add("8120");
        CURPUB.add("8118");
        CURPUB.add("2059");
        CURPUB.add("2060");
        CURPUB.add("2061");
        CURPUB.add("8085");
        CURPUB.add("8087");
        CURPUB.add("8080");
        CURPUB.add("8081");
        CURPUB.add("8083");
        CURPUB.add("8078");
        CURPUB.add("8071");
        CURPUB.add("8073");
        CURPUB.add("8074");
        CURPUB.add("8076");
        CURPUB.add("6180");
        CURPUB.add("1024");
        CURPUB.add("1039");
        CURPUB.add("2062");
        CURPUB.add("2024");
    }

    //用户行为类型 opa
    public static final HashSet<String> OPA = new HashSet<String>();

    static {
        OPA.add("page");
        OPA.add("end");
        OPA.add("duration");
        OPA.add("in");
        OPA.add("pushon");
        OPA.add("od");
        OPA.add("pushoff");
        OPA.add("v");
        OPA.add("openpush");
        OPA.add("ts");
        OPA.add("action");
        OPA.add("login");
        OPA.add("outshow");
        OPA.add("outclick");
        OPA.add("newsdown");
        OPA.add("newsshow");
        OPA.add("pushstatus");
    }

    //错误标志
    public static final int ERRFLAG = -1024;

    //平台类型
    public static final ArrayList<String> PLATFORM = new ArrayList<String>();

    static {
        PLATFORM.add("androidpad");
        PLATFORM.add("iphone");
        PLATFORM.add("ipad");
        PLATFORM.add("windowsphone");
        PLATFORM.add("android");
    }

    //默认值
    public static final String BEANFIELDDEFAULT = "#";

    //实时转换函数
    public static final String JSONTOBEAN = "jsonToBean";
    public static final String ACCOUNTJSONTOBEAN = "accountJsonToBean";
    public static final String CONTENTJSONTOBEAN = "contentJsonToBean";
}































