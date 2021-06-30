package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.bean.RealTimeOverviewResultBean;
import com.scallion.common.Common;
import com.scallion.utils.FunctionUtil;
import com.scallion.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.hash.BloomFilter;
import org.apache.flink.shaded.guava18.com.google.common.hash.Funnels;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * created by gaowj.
 * created on 2021-06-24.
 * function: 实时概况process类
 */
public class RealTimeOverviewProcessWindowFunction extends ProcessWindowFunction<PageAndInfoLogBean, RealTimeOverviewResultBean, String, TimeWindow> {
    //窗口key值
    private ValueState<String> keyState;
    //当日累计分频道UV状态
    private MapState<String, BloomFilter<CharSequence>> dayChUVBloomState;
    private MapState<String, Integer> dayChUVState;
    //频道维度数据
    private ValueState<BloomFilter<CharSequence>> dimChBloomState;
    //当日累计分平台UV状态
    private MapState<String, BloomFilter<CharSequence>> dayPlatUVBloomState;
    private MapState<String, Integer> dayPlatUVState;

    @Override
    public void open(Configuration parameters) throws Exception {
        //初始化窗口key值（按日期分组）
        ValueStateDescriptor<String> keyStateDescriptor = new ValueStateDescriptor<>("keyState", String.class);
        keyState = getRuntimeContext().getState(keyStateDescriptor);
        //初始化当日累计分频道UV状态
        MapStateDescriptor<String, BloomFilter<CharSequence>> dayChUVBloomStateDescriptor = new MapStateDescriptor<>("dayChUVBloomState", TypeInformation.of(String.class), TypeInformation.of(new TypeHint<BloomFilter<CharSequence>>() {
        }));
        dayChUVBloomState = getRuntimeContext().getMapState(dayChUVBloomStateDescriptor);
        MapStateDescriptor<String, Integer> dayChUVStateDescriptor = new MapStateDescriptor<>("dayChUVState", String.class, Integer.class);
        dayChUVState = getRuntimeContext().getMapState(dayChUVStateDescriptor);
        //初始化频道维度状态
        ValueStateDescriptor<BloomFilter<CharSequence>> dimChBloomStateDescriptor = new ValueStateDescriptor<>("dimChBloomState", TypeInformation.of(new TypeHint<BloomFilter<CharSequence>>() {
        }));
        dimChBloomState = getRuntimeContext().getState(dimChBloomStateDescriptor);
        //初始化当日累计分平台UV状态
        MapStateDescriptor<String, BloomFilter<CharSequence>> dayPlatUVStateDescriptor = new MapStateDescriptor<>("dayPlatUVState", TypeInformation.of(String.class), TypeInformation.of(new TypeHint<BloomFilter<CharSequence>>() {
        }));
        dayPlatUVBloomState = getRuntimeContext().getMapState(dayPlatUVStateDescriptor);
        MapStateDescriptor<String, Integer> dayPlatUVOutStateDescriptor = new MapStateDescriptor<>("dayPlatUVState", String.class, Integer.class);
        dayPlatUVState = getRuntimeContext().getMapState(dayPlatUVOutStateDescriptor);
        //定时更新频道维度数据
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                getCHFromOracle();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1800000);//每隔30分钟执行一次
    }

    /**
     * 定时更新频道维度
     */
    private void getCHFromOracle() {
        String sql = "select path from d_news_ch";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1 加载Oracle的jdbc驱动包
            Class.forName(Common.ORACLEJDBCURL);
            //2 建立连接
            connection = DriverManager.getConnection(Common.ORACLEURL, Common.ORACLEUSERNAME, Common.ORACLEPASSWORD);
            //3 创建statement对象，便于执行静态sql语句
            statement = connection.createStatement();
            //4 执行查询
            resultSet = statement.executeQuery(sql);
            //5 处理结果
            while (resultSet.next()) {
                String ch = resultSet.getString("path");
                if (dimChBloomState.value() == null)
                    dimChBloomState.update(BloomFilter.create(Funnels.unencodedCharsFunnel(), 1000, 0.01));
                BloomFilter<CharSequence> bloomFilter = dimChBloomState.value();
                if (!bloomFilter.mightContain(ch)) {
                    bloomFilter.put(ch);
                    dimChBloomState.update(bloomFilter);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 当每个窗口到期时（这个到期指的是满足最大延迟时间后）执行一次
     *
     * @param context
     * @throws Exception
     */
    @Override
    public void clear(Context context) throws Exception {
        long watermark = context.currentWatermark();
        String watermarkDay = TimeUtil.getTimestampToDay(watermark);
        if (TimeUtil.compareDay(watermarkDay, keyState.value()) > 0) {
            keyState.clear();
            dayChUVBloomState.clear();
            dayChUVState.clear();
            dimChBloomState.clear();
            dayPlatUVBloomState.clear();
            dayPlatUVState.clear();
        }
    }

    @Override
    public void process(String key, Context context, Iterable<PageAndInfoLogBean> logIterable, Collector<RealTimeOverviewResultBean> out) throws Exception {
        try {
            //获取当前窗口key值
            if (StringUtils.isBlank(keyState.value()))
                keyState.update(key);

            Iterator<PageAndInfoLogBean> logIterator = logIterable.iterator();
            //每分钟总UV
            BloomFilter<CharSequence> everyMinuteUVBloom = BloomFilter.create(Funnels.unencodedCharsFunnel(), 40000, 0.01);
            int everyMinuteUV = 0;
            //每分钟总PV
            int everyMinutePV = 0;
            //每分钟分频道UV,PV  Tuple2<Integer, Integer>第一位为UV值，第二位为PV值
            HashMap<String, Tuple2<Integer, Integer>> everyMinuteChUVAndPV = new HashMap<>();
            HashMap<String, BloomFilter<CharSequence>> everyMinuteChUVBloom = new HashMap<>();
            //每分钟分平台UV,PV Tuple2<Integer, Integer>第一位为UV值，第二位为PV值
            HashMap<String, Tuple2<Integer, Integer>> everyMinutePlatUVAndPV = new HashMap<>();

            while (logIterator.hasNext()) {
                PageAndInfoLogBean logBean = logIterator.next();
                String userkey = logBean.getUserkey();
                String mos = logBean.getMos();
                String opa = logBean.getOpa();
                String record = logBean.getRecord();

                String platForm = "#";//安卓 or 苹果
                if (mos.contains("android"))
                    platForm = "android";
                else if (mos.contains("iphone"))
                    platForm = "iphone";

                //计算当日累计分频道UV
                if ("page".equals(opa) && record.contains("type=") && !record.contains("ref=back"))
                    addDayChUV(record, userkey);

                if (Common.OPA.contains(opa)) {
                    //计算当日分平台累计UV
                    String platType = FunctionUtil.getMos(mos);
                    if (!"other".equals(platType)) {
                        addDayPlatUV(platType, userkey);
                    }

                    if (!everyMinuteUVBloom.mightContain(userkey)) {
                        everyMinuteUVBloom.put(userkey);
                        //计算每分钟总UV
                        everyMinuteUV = +1;
                        //计算每分钟分平台UV
                        if (!"#".equals(platForm)) {
                            if (everyMinutePlatUVAndPV.containsKey(platForm)) {
                                Tuple2<Integer, Integer> tuple2 = everyMinutePlatUVAndPV.get(platForm);
                                tuple2.f0 += 1;
                            } else {
                                everyMinutePlatUVAndPV.put(platForm, Tuple2.of(1, 0));
                            }
                        }
                    }
                }

                String ref = "";
                if (record.contains("ref="))
                    ref = record.split("ref=")[1].split("\\$")[0].trim();
                //回退操作发ref=back，不计入PV统计
                if ("back".equals(ref))
                    continue;
                //浏览文章单页
                if ("page".equals(opa)
                        && !record.contains("atype=auto")) {
                    String id = record.split("id=")[1].split("\\$")[0].trim();
                    if (id.startsWith("imcp") || id.startsWith("cmpp")
                            || id.startsWith("sub") || id.startsWith("ucms")) {
                        if (id.split("_").length == 3 && id.split("_")[2] != "0") {
                            continue;
                        }
                    }
                    //计算每分钟总PV
                    everyMinutePV += 1;

                    //计算每分钟分平台PV
                    if (!"#".equals(platForm)) {
                        if (everyMinutePlatUVAndPV.containsKey(platForm)) {
                            Tuple2<Integer, Integer> tuple2 = everyMinutePlatUVAndPV.get(platForm);
                            tuple2.f1 += 1;
                        } else {
                            everyMinutePlatUVAndPV.put(platForm, Tuple2.of(0, 1));
                        }
                    }

                    if (ref.startsWith("topic_") || "".equals(ref))
                        continue;
                    //计算每分钟分频道UV,PV
                    addEveryMinuteChUVAndPV(everyMinuteChUVAndPV, everyMinuteChUVBloom, userkey, ref);
                }
                //浏览视频
                if ("v".equals(opa)
                        && record.contains("atype=auto")) {
                    int pdur = 0;
                    int vdur = 0;
                    if (record.contains("pdur="))
                        pdur = Integer.parseInt(record.split("pdur=")[1].split("\\$")[0].trim());
                    if (record.contains("vdur="))
                        vdur = Integer.parseInt(record.split("vdur=")[1].split("\\$")[0].trim());
                    long readRate = 0;
                    if (!(vdur == 0))
                        readRate = Math.round(pdur * 1.0 / vdur);
                    if (readRate >= 0.9 || pdur >= 15) {
                        //计算每分钟总PV
                        everyMinutePV += 1;

                        //计算每分钟分平台PV
                        if (!"#".equals(platForm)) {
                            if (everyMinutePlatUVAndPV.containsKey(platForm)) {
                                Tuple2<Integer, Integer> tuple2 = everyMinutePlatUVAndPV.get(platForm);
                                tuple2.f1 += 1;
                            } else {
                                everyMinutePlatUVAndPV.put(platForm, Tuple2.of(0, 1));
                            }
                        }

                        if (ref.startsWith("topic_") || "".equals(ref))
                            continue;
                        //计算每分钟分频道UV,PV
                        addEveryMinuteChUVAndPV(everyMinuteChUVAndPV, everyMinuteChUVBloom, userkey, ref);
                    }
                }
            }

            //组装结果
            RealTimeOverviewResultBean resultBean = new RealTimeOverviewResultBean();
            resultBean.setTm(TimeUtil.getTimestampToDate(context.window().getStart()));
            resultBean.setEveryMinuteUV(everyMinuteUV);
            resultBean.setEveryMinutePV(everyMinutePV);
            resultBean.setEveryMinuteChUVAndPV(everyMinuteChUVAndPV);
            resultBean.setEveryMinutePlatUVAndPV(everyMinutePlatUVAndPV);
            HashMap<String, Integer> dayChUVMap = new HashMap<>();
            Iterator<String> dayChKeys = dayChUVState.keys().iterator();
            while (dayChKeys.hasNext()) {
                String chKey = dayChKeys.next();
                dayChUVMap.put(chKey, dayChUVState.get(chKey));
            }
            resultBean.setDayChUV(dayChUVMap);
            HashMap<String, Integer> dayPlatUVMap = new HashMap<>();
            Iterator<String> dayPlatKeys = dayPlatUVState.keys().iterator();
            while (dayPlatKeys.hasNext()) {
                String platKey = dayPlatKeys.next();
                dayPlatUVMap.put(platKey, dayPlatUVState.get(platKey));
            }
            resultBean.setDayPlatUV(dayPlatUVMap);
            out.collect(resultBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("RealTimeOverviewProcessWindowFunction is err");
        }
    }

    /**
     * 计算当日累计分频道UV
     *
     * @param record
     * @param userkey
     */
    private void addDayChUV(String record, String userkey) throws Exception {
        //渠道id
        String id = record.split("id=")[1].split("\\$")[0].trim();

        if (dimChBloomState.value().mightContain(id)) {
            String hash = "";
            if ("sy".equals(id))
                hash = String.valueOf(Math.abs(userkey.hashCode()) % 11);
            String hashKey = id + hash;
            if (!dayChUVBloomState.contains(hashKey))
                dayChUVBloomState.put(hashKey, BloomFilter.create(Funnels.unencodedCharsFunnel(), 200000, 0.01));
            BloomFilter<CharSequence> bloomFilter = dayChUVBloomState.get(hashKey);
            if (!bloomFilter.mightContain(userkey)) {
                bloomFilter.put(userkey);
                dayChUVBloomState.put(hashKey, bloomFilter);
                if (!dayChUVState.contains(id))
                    dayChUVState.put(id, 1);
                else
                    dayChUVState.put(id, dayChUVState.get(id) + 1);
            }
        }
    }

    /**
     * 累计分平台当日UV
     *
     * @param platType 平台类型
     * @param userkey
     */
    private void addDayPlatUV(String platType, String userkey) throws Exception {
        String hash = "";
        int expectedInsertions = 0;
        switch (platType) {
            case "android":
                hash = String.valueOf(Math.abs(userkey.hashCode()) % 10);
                expectedInsertions = 200000;
                break;
            case "iphone":
                hash = String.valueOf(Math.abs(userkey.hashCode()) % 2);
                expectedInsertions = 200000;
                break;
            default:
                expectedInsertions = 100000;
        }
        String hashKey = platType + hash;
        if (!dayPlatUVBloomState.contains(hashKey))
            dayPlatUVBloomState.put(hashKey, BloomFilter.create(Funnels.unencodedCharsFunnel(), expectedInsertions, 0.01));
        BloomFilter<CharSequence> bloomFilter = dayPlatUVBloomState.get(hashKey);
        if (!bloomFilter.mightContain(userkey)) {
            bloomFilter.put(userkey);
            dayPlatUVBloomState.put(hashKey, bloomFilter);
            if (!dayPlatUVState.contains(platType))
                dayPlatUVState.put(platType, 1);
            else
                dayPlatUVState.put(platType, dayPlatUVState.get(platType) + 1);
        }
    }


    /**
     * 计算每分钟分频道UV,PV
     *
     * @param everyMinuteChUVAndPV
     * @param everyMinuteChUVBloom
     * @param userKey
     * @param ref
     */
    private void addEveryMinuteChUVAndPV(HashMap<String, Tuple2<Integer, Integer>> everyMinuteChUVAndPV, HashMap<String, BloomFilter<CharSequence>> everyMinuteChUVBloom, String userKey, String ref) {
        if (everyMinuteChUVBloom.containsKey(ref)) {
            //第一位为UV值，第二位为PV值
            Tuple2<Integer, Integer> uvAndPv = everyMinuteChUVAndPV.get(ref);
            BloomFilter<CharSequence> bloomFilter = everyMinuteChUVBloom.get(ref);

            if (!bloomFilter.mightContain(userKey)) {
                bloomFilter.put(userKey);
                uvAndPv.f0 += 1;
            }
            uvAndPv.f1 += 1;
        } else {
            BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.unencodedCharsFunnel(), 40000, 0.01);
            bloomFilter.put(userKey);

            everyMinuteChUVBloom.put(ref, bloomFilter);
            everyMinuteChUVAndPV.put(ref, Tuple2.of(1, 1));
        }
    }
}



























