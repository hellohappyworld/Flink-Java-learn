package com.scallion.utils;

import java.text.ParseException;
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
    private static SimpleDateFormat dateToTimestampSdf;
    private static Date date;

    static {
        sdf = new SimpleDateFormat("HH:mm:ss");
        dateToTimestampSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();
    }

    public static String getTimestampToDate(long timeStamp) {
        date.setTime(timeStamp);
        String format = sdf.format(date);
        return format;
    }

    public static long getDateToTimestamp(String date) {
        long timestamp;
        try {
            timestamp = dateToTimestampSdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return Long.MIN_VALUE;
        }
        return timestamp;
    }

    public static void main(String[] args) {
        /*String timestampToDate = TimeUtil.getTimestampToDate(1621584720000L);
        System.out.println(timestampToDate);*/
        long dateToTimestamp = TimeUtil.getDateToTimestamp("2021-06-23 14:45:43");
        System.out.println(dateToTimestamp);
    }
}
