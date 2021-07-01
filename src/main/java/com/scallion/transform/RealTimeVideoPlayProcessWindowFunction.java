package com.scallion.transform;

import com.scallion.bean.PageAndInfoLogBean;
import com.scallion.bean.RealTimeVideoPlayResultBean;
import com.scallion.common.Common;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * created by gaowj.
 * created on 2021-07-01.
 * function: 实时播放process函数
 */
public class RealTimeVideoPlayProcessWindowFunction extends ProcessWindowFunction<PageAndInfoLogBean, RealTimeVideoPlayResultBean, String, TimeWindow> {
    @Override
    public void process(String key, Context context, Iterable<PageAndInfoLogBean> logsIterable, Collector<RealTimeVideoPlayResultBean> out) throws Exception {
        try {
            //播放量
            int playCount = 0;
            //播放成功量
            int playSuccessCount = 0;
            //视频曝光量
            int videoInfoCount = 0;

            Iterator<PageAndInfoLogBean> logsIterator = logsIterable.iterator();
            while (logsIterator.hasNext()) {
                PageAndInfoLogBean logBean = logsIterator.next();
                String opa = logBean.getOpa();
                String record = logBean.getRecord();
                if ((Common.OPATYPES.get("v")).equals(opa)) {
                    playCount += 1;
                    if (record.contains("yn=yes"))
                        playSuccessCount += 1;
                } else if ((Common.OPATYPES.get("pageinfo")).equals(opa)) {
                    String docId = "";
                    if (record.contains("pinfo=")) {
                        docId = record.split("pinfo=")[1].split(":")[0];
                    }
                    if (!"".equals(docId) && docId.startsWith("video"))
                        videoInfoCount += 1;
                }
            }

            //组装结果Bean
            RealTimeVideoPlayResultBean resultBean = new RealTimeVideoPlayResultBean();
            resultBean.setPlayCount(playCount);
            resultBean.setPlaySuccessCount(playSuccessCount);
            resultBean.setVideoInfoCount(videoInfoCount);

            out.collect(resultBean);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
