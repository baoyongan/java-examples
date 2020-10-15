package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.*;

public class MyBean2 {
    public int id;
    private String name;

    public MyBean2() {
    }

    public MyBean2(int i, String name) {
        this.id=i;
        this.name=name;
    }

    @JsonProperty("name") // 当我们处理非标准的getter和setter时，让我们使用@JsonProperty来序列化/反序列化属性名
    public String getTheName() {
        return name;
    }


    @JsonProperty("name") // 当我们处理非标准的getter和setter时，让我们使用@JsonProperty来序列化/反序列化属性名
    public void setTheName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}