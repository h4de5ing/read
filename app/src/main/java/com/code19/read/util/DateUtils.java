package com.code19.read.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by h4de5ing 2016/5/18 018
 */
public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化日期
     *
     * @param date long型时间
     * @return 格式化后的日期字符串
     */
    public static String formatDate(long date) {
        return DATE_FORMAT_DATETIME.format(new Date(date));
    }

}
