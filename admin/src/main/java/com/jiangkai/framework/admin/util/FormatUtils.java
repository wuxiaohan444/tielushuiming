package com.jiangkai.framework.admin.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
public class FormatUtils {
    private final static long BEIJING_TIMEOFFSET = 8 * 60 * 60 * 1000;

    /**
     * @param
     * @return
     * @Description 分钟时间转换成 时分秒
     * @date 2019/6/14
     * @auther zvbb
     */
    public static String minTime2HMS(BigDecimal minute) {
        int h = minute.intValue() / (60);
        int m = minute.intValue() - (h * 60);
        String result;
        if (m < 10) {
            result = h + ":0" + m + ":" + "00";
        } else {
            result = h + ":" + m + ":" + "00";
        }
        return result;
    }

    public static String msTime2HMS(Long ms) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms - BEIJING_TIMEOFFSET);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    public static String minTime2HMS(Long minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(minute * 60 * 1000 - BEIJING_TIMEOFFSET);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    public static String secondTime2HMS(Long s) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(s * 1000 - BEIJING_TIMEOFFSET);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
}
