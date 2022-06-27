package com.redis.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ProductUtils {
    public static long getTimeRemaining() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) , 23 , 59 , 59);
//        return (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())  / 1000;
        return ChronoUnit.SECONDS.between( LocalDateTime.now() , LocalDate.now().atTime(LocalTime.MAX));
    }
}
