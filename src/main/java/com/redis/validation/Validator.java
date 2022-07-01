package com.redis.validation;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.utils.MessageUtils;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Validator {
    public static void validateSaveProd(Product product) {
        if (Objects.isNull(product.getTransactionNo())) {
            throw new ProductException(MessageUtils.getMessage(Constant.TRANS_NO_NULL));
        }
        if (product.getQty() < 0) {
            throw new ProductException(MessageUtils.getMessage(Constant.QTY_WRONG));
        }
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
