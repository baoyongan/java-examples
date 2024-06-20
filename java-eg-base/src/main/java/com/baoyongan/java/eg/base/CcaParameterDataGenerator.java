package com.baoyongan.java.eg.base;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CcaParameterDataGenerator {
    public static void main(String[] args) {
        // 生成样例数据的数量
        int dataCount = 500;

        // 定义参数代码的前缀和后缀
        String paramCodePrefix = "param_code_";
        String paramCodeSuffix = "";
        // 定义参数名称的前缀和后缀
        String paramNamePrefix = "param_name_";
        String paramNameSuffix = "";
        // 定义参数值的前缀和后缀
        String paramValuePrefix = "param_value_";
        String paramValueSuffix = "";
        // 定义参数描述的前缀和后缀
        String paramDescPrefix = "param_desc_";
        String paramDescSuffix = "";

        // 生成参数代码
        Random random = new Random();
        for (int i = 0; i < dataCount; i++) {
            // 生成随机的参数代码
            String paramCode = paramCodePrefix + random.nextInt(100000) + paramCodeSuffix;
            // 生成随机的参数名称
            String paramName = paramNamePrefix + random.nextInt(100000) + paramNameSuffix;
            // 生成随机的参数值
            String paramValue = paramValuePrefix + random.nextInt(100000) + paramValueSuffix;
            // 生成随机的参数描述
            String paramDesc = paramDescPrefix + random.nextInt(100000) + paramDescSuffix;

            // 组合成插入 SQL 语句
            String sql = "INSERT INTO cca_parameter (param_code, param_name, param_value, param_desc) VALUES ('"
                    + paramCode + "', '" + paramName + "', '" + paramValue + "', '" + paramDesc + "');";

            // 将生成的 SQL 语句写入到文本文件
            try {
                FileWriter writer = new FileWriter("D:/tmp/data.sql", true);
                writer.write(sql + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}