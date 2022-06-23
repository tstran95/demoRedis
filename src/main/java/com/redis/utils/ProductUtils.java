package com.redis.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductUtils {
    public static long getTimeRemaining() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = (new Date().toString());

        try {
            Date date = dateFormat.parse(dateStr.substring(dateStr.indexOf(":") - 2, dateStr.lastIndexOf(":") +2) );
            Date reference = dateFormat.parse("23:59:59");
            return (reference.getTime() - date.getTime()) / 1000;
        }catch (ParseException e) {
            throw e;
        }
    }
}
