package com.redis.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ProductUtils {
    public static long getTimeRemaining() {
        return ChronoUnit.SECONDS.between( LocalDateTime.now() , LocalDate.now().atTime(LocalTime.MAX));
    }
}
