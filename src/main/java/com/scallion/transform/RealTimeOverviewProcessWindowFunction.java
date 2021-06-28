package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.bean.RealTimeOverviewResultBean;
import com.scallion.common.Common;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.hash.BloomFilter;
import org.apache.flink.shaded.guava18.com.google.common.hash.Funnels;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Iterator;

/**
 * created by gaowj.
 * created on 2021-06-24.
 * function: 实时概况process类
 * origin ->
 */
public class RealTimeOverviewProcessWindowFunction extends ProcessWindowFunction<PageAndInfoLogBean, RealTimeOverviewResultBean, String, TimeWindow> {
    @Override
    public void open(Configuration parameters) throws Exception {

    }

    @Override
    public void clear(Context context) throws Exception {

    }

    @Override
    public void process(String key, Context context, Iterable<PageAndInfoLogBean> logIterable, Collector<RealTimeOverviewResultBean> out) throws Exception {
        try {

            getEveryMinutePV(logIterable);

            //4、每分钟 分平台UV,PV


            //当日累计UV
            //当日分机型累计UV
            //当日分频道累计UV
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("RealTimeOverviewProcessWindowFunction is err");
        }
    }

    /**
     * 每分钟总PV
     * 每分钟分频道UV,PV
     *
     * @param logIterable
     * @return
     */
    private Tuple4<Integer, Integer, HashMap<String, Tuple2<Integer, Integer>>, HashMap<String, Tuple2<Integer, Integer>>> getEveryMinutePV(Iterable<PageAndInfoLogBean> logIterable) {
        try {
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

                String platForm = "#";//安卓 or 苹果
                String mos = logBean.getMos();
                if (mos.contains("android"))
                    platForm = "android";
                else if (mos.contains("iphone"))
                    platForm = "iphone";


                if (Common.OPA.contains(logBean.getOpa())
                        && !everyMinuteUVBloom.mightContain(logBean.getUserkey())) {
                    everyMinuteUVBloom.put(logBean.getUserkey());
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

                String ref = "";
                if (logBean.getRecord().contains("ref="))
                    ref = logBean.getRecord().split("ref=")[1].split("\\$")[0].trim();
                //回退操作发ref=back，不计入PV统计
                if ("back".equals(ref))
                    continue;
                //浏览文章单页
                if ("page".equals(logBean.getOpa())
                        && !logBean.getRecord().contains("atype=auto")) {
                    String id = logBean.getRecord().split("id=")[1].split("\\$")[0].trim();
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
                    addEveryMinuteChUVAndPV(everyMinuteChUVAndPV, everyMinuteChUVBloom, logBean, ref);
                }
                //浏览视频
                if ("v".equals(logBean.getOpa())
                        && logBean.getRecord().contains("atype=auto")) {
                    int pdur = 0;
                    int vdur = 0;
                    if (logBean.getRecord().contains("pdur="))
                        pdur = Integer.parseInt(logBean.getRecord().split("pdur=")[1].split("\\$")[0].trim());
                    if (logBean.getRecord().contains("vdur="))
                        vdur = Integer.parseInt(logBean.getRecord().split("vdur=")[1].split("\\$")[0].trim());
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
                        addEveryMinuteChUVAndPV(everyMinuteChUVAndPV, everyMinuteChUVBloom, logBean, ref);
                    }
                }
            }

            return Tuple4.of(everyMinuteUV, everyMinutePV, everyMinuteChUVAndPV, everyMinutePlatUVAndPV);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getEveryMinutePV is err");
            return Tuple4.of(null, null, null, null);
        }
    }

    /**
     * 计算每分钟分频道UV,PV
     *
     * @param everyMinuteChUVAndPV
     * @param everyMinuteChUVBloom
     * @param logBean
     * @param ref
     */
    private void addEveryMinuteChUVAndPV(HashMap<String, Tuple2<Integer, Integer>> everyMinuteChUVAndPV, HashMap<String, BloomFilter<CharSequence>> everyMinuteChUVBloom, PageAndInfoLogBean logBean, String ref) {
        if (everyMinuteChUVBloom.containsKey(ref)) {
            //第一位为UV值，第二位为PV值
            Tuple2<Integer, Integer> uvAndPv = everyMinuteChUVAndPV.get(ref);
            BloomFilter<CharSequence> bloomFilter = everyMinuteChUVBloom.get(ref);

            if (!bloomFilter.mightContain(logBean.getUserkey())) {
                bloomFilter.put(logBean.getUserkey());
                uvAndPv.f0 += 1;
            }
            uvAndPv.f1 += 1;
        } else {
            BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.unencodedCharsFunnel(), 40000, 0.01);
            bloomFilter.put(logBean.getUserkey());

            Tuple2<Integer, Integer> tuple2 = new Tuple2<>();
            tuple2.f0 = 1;
            tuple2.f1 = 1;

            everyMinuteChUVBloom.put(ref, bloomFilter);
            everyMinuteChUVAndPV.put(ref, tuple2);
        }
    }
}



























