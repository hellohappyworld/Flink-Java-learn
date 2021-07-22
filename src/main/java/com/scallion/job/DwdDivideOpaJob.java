package com.scallion.job;

/**
 * created by gaowj.
 * created on 2021-07-22.
 * function:按照用户行为(opa)建模划分dwd层明细数据
 */
public class DwdDivideOpaJob implements Job {
    @Override
    public void run() {
        //一、Source
        //从dwdLogTmp中获取数据
        //二、Transform
        //1、按照用户行为(opa)将数据划分出
        // page readrate duration pageinfo action adclick adinfo hb ts pushon pushoff
        // dwspot dwact dwinfo gexiang
        //2、将划分出的各行为日志与相应维表关联生成明细数据
        // page readrate duration 生成 dwdPage(用户点击明细)
        // pageinfo 生成 dwdPageInfo(用户曝光明细)
        // action 生成 dwdButton(按钮点击明细)
        // action && record.type=('btnsub','btnunsub') 生成 dwdSub(用户订阅或取消订阅明细)
        // action && type='store' 生成 dwdStore(文章收藏明细)
        // adclick 生成 dwdAdClick(用户点击广告明细)
        // adinfo 生成 dwdAdInfo(广告曝光明细)
        // hb 生成 dwdHb(用户联网明细)
        // ts 生成 dwdShare(文章分享明细)
        // dwspot dwact dwinfo 生成 dwdDw(端外用户操作明细)
        // pushon pushoff 生成 dwdPush(推送开关明细)
        // gexiang 生成 dwdGeXiang(杂项明细)
        //三、Sink
        //将上述各个明细数据写入相应Kafka中
    }
}
