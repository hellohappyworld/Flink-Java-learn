package com.scallion.transform;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * created by gaowj.
 * created on 2021-05-24.
 * function: 自定义窗口触发器
 * origin ->
 */
public class CustomProcessingTimeTrigger extends Trigger<Object, TimeWindow> {
    private static final long serialVersionUID = 1L;

    private CustomProcessingTimeTrigger() {
    }

    private static int flag = 0;

    @Override
    public TriggerResult onElement(Object element, long timestamp, TimeWindow window, TriggerContext ctx) {
        ctx.registerProcessingTimeTimer(window.maxTimestamp());
        // CONTINUE是代表不做输出，也即是，此时我们想要实现比如10条输出一次
        // 而不是窗口结束再输出就可以在这里实现
        if (flag > 9) {
            flag = 0;
            return TriggerResult.FIRE;
        } else {
            flag++;
        }
        System.out.println("onElement : " + element);
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) {
        return TriggerResult.FIRE;
    }

    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {
        ctx.deleteProcessingTimeTimer(window.maxTimestamp());
    }

    @Override
    public boolean canMerge() {
        return true;
    }

    @Override
    public void onMerge(TimeWindow window,
                        OnMergeContext ctx) {
        //仅在时间尚未超过合并窗口的结束时才注册计时器，这与onElement（）中的逻辑一致。
        // 如果时间已过，则将触发onElement（）窗口，并在此处设置计时器将触发两次窗口。
        long windowMaxTimestamp = window.maxTimestamp();
        if (windowMaxTimestamp > ctx.getCurrentProcessingTime()) {
            ctx.registerProcessingTimeTimer(windowMaxTimestamp);
        }
    }

    @Override
    public String toString() {
        return "ProcessingTimeTrigger()";
    }

    /**
     * Creates a new trigger that fires once system time passes the end of the window.
     */
    public static CustomProcessingTimeTrigger create() {
        return new CustomProcessingTimeTrigger();
    }

}
