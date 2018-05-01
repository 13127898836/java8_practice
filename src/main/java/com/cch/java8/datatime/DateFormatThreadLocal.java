package com.cch.java8.datatime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cch
 * 2018-05-01 22:09.
 */

public class DateFormatThreadLocal {
    private static final ThreadLocal<DateFormat>df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static Date convert(String source)throws Exception{
        return df.get().parse(source);

    }
}
