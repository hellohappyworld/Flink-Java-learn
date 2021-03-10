package com.scallion.transform;

import com.scallion.bean.WordWithCount;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * created by gaowj.
 * created on 2021-03-01.
 * function:
 * origin ->
 */
public class SplitterFlatMapFunction implements FlatMapFunction<String, WordWithCount> {

    @Override
    public void flatMap(String sentence, Collector<WordWithCount> collector) throws Exception {
        /*for (String word : sentence.split(" "))
            collector.collect(new WordWithCount(word, 1L));*/
        collector.collect(new WordWithCount(sentence, 1L));
    }
}
