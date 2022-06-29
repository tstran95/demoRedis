package com.redis.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class TransInfoUtils {
    public static String generateNumber() {
        Random random = new Random();
        int num = random.nextInt(1_000_000);
        StringBuilder numStr = new StringBuilder(Integer.toString(num));
        while (numStr.length() < 6) {
            numStr.insert(0, "0");
        }
        return String.valueOf(numStr);
    }

    public static String checkDone(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(generateNumber());
        return String.valueOf(stringBuilder);
    }

    public static String checkDuplicate(String autoGenStr, List<String> listValue, int count) {
        log.info("TransInfoServiceImpl checkDuplicate START with autoGenStr {}", autoGenStr);
        for (int i = count; i > 0; i--) {
            boolean flag = false;
            for (String str : listValue) {
                if (autoGenStr.equals(str)) {
                    flag = true;
                    log.info("TransInfoServiceImpl checkDuplicate Duplicate result!");
                    autoGenStr = TransInfoUtils.generateNumber();
                    break;
                }
            }
            if (!flag) {
                log.info("TransInfoServiceImpl checkDuplicate END with autoGenStr {}", autoGenStr);
                return autoGenStr;
            }
        }
        log.info("TransInfoServiceImpl checkDuplicate END with autoGenStr is null");
        return null;
    }
}
