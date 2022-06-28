package com.redis.utils;

import java.util.List;
import java.util.Random;

public class TransInfoUtils {
    public static String generateNumber() {
        Random random = new Random();
        Integer num = random.nextInt(1000000);
        StringBuilder numStr = new StringBuilder(num.toString());
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

    public static String recall(String str1, List<String> stringList, int count) {
        for (int i = count; i > 0 ; i--) {
            String strCompare = checkDone(str1);
            boolean flag = true;
            for (int j = 0; j < stringList.size(); j++) {
                if (strCompare.equals(stringList.get(j))) {
                    flag = false;
                    System.out.println("Duplicate!!!!");
                    break;
                }
            }
            if (flag) {
                return strCompare;
            }
        }
        System.out.println("Cant gen code");
        return null;
    }
}
