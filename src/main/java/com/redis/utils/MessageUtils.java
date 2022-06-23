//package com.redis.utils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class MessageUtils {
//    private static final String FILE_CONFIG = "\\message\\messageError.properties";
//
//    public static String getMessage(String code) {
//        Properties properties = new Properties();
//        InputStream inputStream = null;
//        try {
//            inputStream = MessageUtils.class.getClassLoader()
//                    .getResourceAsStream(FILE_CONFIG);
//
//            // load properties from file
//            properties.load(inputStream);
//
//            // get property by name
//            return properties.getProperty(code);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // close objects
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
