package com.redis.validation;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.exception.VNPAYException;
import com.redis.model.TransInfo;
import com.redis.utils.MessageUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Random;

@Component
@Data
public class Validator {

    public static void validateSaveProd(Product product) {
        if (Objects.isNull(product.getTransactionNo())) {
            throw new ProductException(MessageUtils.getMessage(Constant.TRANS_NO_NULL));
        }
        if (product.getQty() < 0) {
            throw new ProductException(MessageUtils.getMessage(Constant.QTY_WRONG));
        }
    }

    public static void validateSaveTrans(TransInfo transInfo) {
        if (Constant.EMPTY.equals(transInfo.getUserName())) {
            throw new VNPAYException(MessageUtils.getMessage(Constant.USER_NAME_EMPTY));
        }
        if (transInfo.getAmount() <= 0) {
            throw new VNPAYException(MessageUtils.getMessage(Constant.AMOUNT_WRONG));
        }
        if (!checkNumber(transInfo.getPhoneNumber())) {
            throw new VNPAYException(MessageUtils.getMessage(Constant.PHONE_WRONG));
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
