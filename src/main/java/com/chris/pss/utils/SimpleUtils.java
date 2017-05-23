package com.chris.pss.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zht on 2017/5/19.
 */
public class SimpleUtils {

    public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    public static String date2String(long time) {
        return new SimpleDateFormat(DATE_FORMATE).format(new Date(time));
    }

    public static long String2Date(String time) {
        try {
            if (!EmptyUtils.isEmpty(time)) {
                return new SimpleDateFormat(DATE_FORMATE).parse(time).getTime();
            }
        } catch (ParseException e) {
        }
        return 0;
    }
}
