package com.redis.validation;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
    public static void checkSaveProd(Product product) {
        //TODO
    }

    public static boolean validateTime(String input) {
        boolean result = true;
        try {
            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.parse(input);
        }catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static boolean checkNumber(String input) {
        boolean result = true;
        try {
            Integer.parseInt(input);
        }catch (Exception e) {
            result = false;
        }
        return result;
    }
}
