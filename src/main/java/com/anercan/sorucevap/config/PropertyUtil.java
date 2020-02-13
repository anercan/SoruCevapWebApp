package com.anercan.sorucevap.config;

import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

public class PropertyUtil {

    protected static ConcurrentHashMap<String, String> properties = new ConcurrentHashMap<>();

    public static String getStringValue(String key) {
        return properties.get(key.toLowerCase());
    }

    public static String getStringValue(String key, String defaultVal) {
        String value = getStringValue(key);
        return StringUtils.isEmpty(value) ? defaultVal : value;
    }

    public static Integer getIntegerValue(String key, Integer defaultVal) {
        String value = getStringValue(key);
        return StringUtils.isEmpty(value) ? defaultVal : Integer.parseInt(value);
    }

    public static Boolean getBooleanValue(String key, Boolean defaultVal) {
        String value = getStringValue(key);
        return StringUtils.isEmpty(value) ? defaultVal : Boolean.parseBoolean(value);
    }

    public static Long getLongValue(String key, Long defaultVal) {
        String value = getStringValue(key);
        return StringUtils.isEmpty(value) ? defaultVal : Long.parseLong(value);
    }

    public static void addProperty(String key, String val) {
        properties.put(key.toLowerCase(), val);
    }

    public static Boolean isEmpty(){
        return properties.isEmpty();
    }

}
