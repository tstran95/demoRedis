package com.redis.validation;

import com.redis.entity.Product;

import java.text.SimpleDateFormat;

public class Validator {
    public static void validateSaveProd(Product product) {
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
