package com.anercan.sorucevap.config;

import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

public class PropertyUtil {

    protected static ConcurrentHashMap<String, String> properties = new ConcurrentHashMap<>();
    protected static ConcurrentHashMap<String, String> clientProperties = new ConcurrentHashMap<>();

    public static String getClientProp(String key) {
        if (clientProperties.containsKey(key)) {
            return clientProperties.get(key);
        }
        return key;
    }

    public static String getStringValue(String key) {
        return properties.get(key);
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
        properties.put(key, val);
    }

    public static void addClientPropery(String key, String val) {
        clientProperties.put(key, val);
    }

    public static Boolean isEmpty() {
        return properties.isEmpty();
    }

}
