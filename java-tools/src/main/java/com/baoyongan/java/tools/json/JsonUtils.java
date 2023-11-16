package com.baoyongan.java.tools.json;

import org.apache.commons.lang3.StringUtils;

public class JsonUtils {
    public static boolean isJsonArray(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        if (jsonString.trim().charAt(0) == '[') {
            return true;
        }
        return false;
    }

    public static boolean isJsonObject(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        if (jsonString.trim().charAt(0) == '{') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.printf("是否数组：%b", isJsonArray("[]"));
        System.out.printf("是否对象：%b", isJsonObject("{}"));
        System.out.printf("是否数组：%b", isJsonArray(" []"));
        System.out.printf("是否对象：%b", isJsonObject("1[]"));
    }
}
