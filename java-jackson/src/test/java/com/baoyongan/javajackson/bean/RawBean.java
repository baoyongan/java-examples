package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class RawBean {
    public String name;
 
    @JsonRawValue  // 按属性原样序列化
    public String json;

    public RawBean(String name, String json) {
        this.name=name;
        this.json=json;
    }
}