package com.jiangkai.framework.admin.util;

import com.jiangkai.framework.utils.tools.IStringUtils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.Callable;

/**
 * @author Administrator
 * @date 2018/8/17 14:30
 */
public abstract class DateUtils {


    public static Date parse(String text) {
        if (IStringUtils.isEmpty(text)) {
            return null;
        }
        SimpleDateFormat sdf;
        if (text.length() >= 24) {
            if (text.contains(".")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
            }
        } else if (text.length() >= 23) {
            if (text.contains(".")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            }
        } else if (text.length() >= 19) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (text.length() >= 14) {
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        } else if (text.length() >= 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if (text.length() >= 8) {
            sdf = new SimpleDateFormat("HH:mm:ss");
        } else if (text.length() == 7) {
            sdf = new SimpleDateFormat("yyyy-MM");
        } else if (text.length() >= 5) {
            sdf = new SimpleDateFormat("HH:mm");
        } else {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * end 比start 多的天数
     */
    public static int differentDays(Date start, Date end) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            return day2 - day1;
        }
    }

    /**
     * 获取指定Date的开始时间
     */
    public static Date getStartOfDay(Date date) {
        return DateUtils.getDateOfDay(date, true);
    }

    private static Date getDateOfDay(Date date, boolean min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (!min)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定Date的结束时间
     */
    public static Date getEndOfDay(Date date) {
        return DateUtils.getDateOfDay(date, false);
    }

    /**
     * 获取当月的第一天
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月的第一天
     */
    public static Date getDayZeroHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getSepcialEndMonth(Integer year, Integer halfYear, Integer quarter, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        if (!Objects.isNull(year)) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, 11);
        }

        if (!Objects.isNull(halfYear)) {
            calendar.set(Calendar.MONTH, halfYear * 6 - 1);
        }

        if (!Objects.isNull(quarter)) {
            calendar.set(Calendar.MONTH, quarter * 3 - 1);
        }
        if (!Objects.isNull(month)) {
            calendar.set(calendar.MONTH, month - 1);
        }
        return calendar.getTime();
    }

    /**
     * @Description 返回指定月份的第一天
     */
    public static Date getSpecialMonth(Integer year, Integer halfYear, Integer quarter, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        if (!Objects.isNull(year)) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, 0);
        }

        if (!Objects.isNull(halfYear)) {
            calendar.set(Calendar.MONTH, (halfYear - 1) * 6);
        }

        if (!Objects.isNull(quarter)) {
            calendar.set(Calendar.MONTH, (quarter - 1) * 3);
        }
        if (!Objects.isNull(month)) {
            calendar.set(calendar.MONTH, month - 1);
        }
        return calendar.getTime();
    }

    /**
     * @Description 返回某月的天数
     */
    public static int getMonthDayCnt(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int cnt = calendar.get(Calendar.DAY_OF_MONTH);
        return cnt;
    }

    /**
     * @Description 返回日期的月的最后一天
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return calendar.getTime();
    }

    public static long UTCToCST(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        return calendar.getTimeInMillis();
    }

    public static long CSTToUTC(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 8);
        return calendar.getTimeInMillis();
    }

    public static void main(String[] args) {
        System.out.println(getMonthLastDay(new Date()));
    }

}
