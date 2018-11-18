package com.enroll.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author hsc
 *
 */
public class DateUtils {
	/** 时间格式(yyyy) */
	public final static String DATE_YEAR_PATTERN = "yyyy";
	/** 时间格式(yyyy) */
	public final static String DATE_YEAR_MONTH_PATTERN = "yyyy-MM";
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }
	
	public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }
	
	public static String getYear(Date date) {
        return format(date, DATE_YEAR_PATTERN);
    }
	
	public static String getYearMonth(Date date) {
        return format(date, DATE_YEAR_MONTH_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
}

