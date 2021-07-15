package com.baoyongan.java.eg.base.json_ch;

import com.alibaba.fastjson.JSON;

public class ConvertApp {

    public static void main(String[] args) {
        String ss="{\"name\":\"123\",\"age\":12}";
        MapperBean mapperBean = JSON.parseObject(ss, MapperBean.class);
        System.out.println(mapperBean);
    }
}
