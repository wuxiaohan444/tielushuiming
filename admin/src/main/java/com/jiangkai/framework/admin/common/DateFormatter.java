package com.jiangkai.framework.admin.common;

import com.jiangkai.framework.admin.util.DateUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Administrator
 * @date 2018/8/15 11:12
 * Not annotated method overrides method annotated with @NonNullApi
 */
@SuppressWarnings("all")
public class DateFormatter implements Formatter<Date> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return DateUtils.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return sdf.format(object);
    }
}
