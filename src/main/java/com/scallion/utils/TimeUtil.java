package com.scallion.utils;

import com.scallion.common.Common;

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
    private static SimpleDateFormat timestampToDateSdf;
    private static SimpleDateFormat daySdf;
    private static Date date;

    static {
        sdf = new SimpleDateFormat("HH:mm:ss");
        dateToTimestampSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestampToDateSdf = new SimpleDateFormat("yyyy-MM-dd");
        daySdf = new SimpleDateFormat("yyyy-MM-dd");
        date = new Date();
    }

    public static String getTimestampToDay(long timeStamp) {
        date.setTime(timeStamp);
        return timestampToDateSdf.format(date);
    }

    public static int compareDay(String day1, String day2) {
        try {
            Date date1 = daySdf.parse(day1);
            Date date2 = daySdf.parse(day2);
            //大于0代表date1在date2之后 小于0代表在之前 等于0代表两日期相同
            return date1.compareTo(date2);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Common.ERRFLAG;
        }
    }

    public static String getTimestampToDate(long timeStamp) {
        date.setTime(timeStamp);
        String format = sdf.format(date);
        return format;
    }

    public static String getTimestampToDateStr(long timeStamp) {
        date.setTime(timeStamp);
        String format = dateToTimestampSdf.format(date);
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
        String day1 = TimeUtil.getTimestampToDateStr(1624958205715L);
        System.out.println(day1);
    }
}
