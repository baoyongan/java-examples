package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {

    @JacksonInject // 表示属性将从注入中而不是从JSON数据中获得其值
    public int id;
     
    public String name;

    @Override
    public String toString() {
        return "BeanWithInject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}