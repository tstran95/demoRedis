package com.redis.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ProductUtils {
    public static long getTimeRemaining() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) , 23 , 59 , 59);
        return (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())  / 1000;
    }
}
