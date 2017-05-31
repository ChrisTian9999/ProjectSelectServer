package com.chris.pss.utils;

import com.chris.pss.entity.BaseResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zht on 2017/5/19.
 */
public class SimpleUtils {

    public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    public static String date2String(long time) {
        return new SimpleDateFormat(DATE_FORMATE).format(new Date(time));
    }


    /**
     * 默认的返回状态的response
     */
    public static BaseResponse<Map> generalResponseState(boolean flag) {
        if (!flag) {
            return new BaseResponse<Map>(Const.ERROR_NOT_FOUND, "失败", null);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        return new BaseResponse<Map>(map);
    }

    /**
     *
     */
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
