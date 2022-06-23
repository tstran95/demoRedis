package com.redis.validation;

import com.redis.exception.ProductException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
    public static void checkTime(String input) {
        //checkTime
        //throw ProductEx("Hong check duoc thoi gian")

        //checkFormat
        //throw ProductEx("Hong ckeck duoc format")


        try {
            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date inputDate = formatter.parse(input);
            String dateToStr = input.substring(0 , input.indexOf(":") - 2) + "23:59:59";
            Date endTheDay = formatter.parse(dateToStr);
            // thực hiện validate và throw new ProductException("Mã lỗi")


        }catch (ParseException e) {
            throw new ProductException("Cant Parse!!!!!!");
        }
    }
}
