package com.scallion.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by gaowj.
 * created on 2021-03-10.
 * function:
 * origin ->
 */
public class TimeUtil {
    private static SimpleDateFormat sdf;
    private static Date date;

    static {
        sdf = new SimpleDateFormat("HH:mm:ss");
        date = new Date();
    }

    public static String getTimestampToDate(long timeStamp) {
        date.setTime(timeStamp);
        String format = sdf.format(date);
        return format;
    }

    public static void main(String[] args) {
        String timestampToDate = TimeUtil.getTimestampToDate(1615367993634L);
        System.out.println(timestampToDate);
    }
}
