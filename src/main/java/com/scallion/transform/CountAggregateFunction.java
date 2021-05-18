package com.scallion.transform;

import com.scallion.bean.CountAccumulator;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * created by gaowj.
 * created on 2021-05-18.
 * function: AggregateFunction
 * origin ->
 */
public class CountAggregateFunction implements AggregateFunction<Tuple3<String, String, String>, CountAccumulator, String> {
    @Override
    public CountAccumulator createAccumulator() {
        return new CountAccumulator();
    }

    @Override
    public CountAccumulator add(Tuple3<String, String, String> input, CountAccumulator countAccumulator) {
        String userkey = input.f0;
        String tm = input.f1;

        countAccumulator.setUserKey(userkey);
        countAccumulator.setCount(countAccumulator.getCount() + 1);
        if (countAccumulator.getStartTm() == null) {
            countAccumulator.setStartTm(tm);
            countAccumulator.setEndTm(tm);
        } else {
            if (tm.compareTo(countAccumulator.getEndTm()) > 0)
                countAccumulator.setEndTm(tm);
        }

        return countAccumulator;
    }

    @Override
    public CountAccumulator merge(CountAccumulator acc1, CountAccumulator acc2) {
        acc1.setCount(acc1.getCount() + acc2.getCount());
        if (acc1.getStartTm().compareTo(acc2.getStartTm()) > 0)
            acc1.setStartTm(acc2.getStartTm());
        if (acc1.getEndTm().compareTo(acc2.getEndTm()) < 0)
            acc1.setEndTm(acc2.getEndTm());
        return acc1;
    }

    @Override
    public String getResult(CountAccumulator countAccumulator) {
        return countAccumulator.toString();
    }
}
