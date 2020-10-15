package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "name", "id" }) // 指定序列化的顺序
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyBean {
    public int id;
    private String name;

    public MyBean() {
    }

    public MyBean(int i, String name) {
        this.id=i;
        this.name=name;
    }

    @JsonGetter("name") // 指定该方法为属性的getter 方法
    public String getTheName() {
        return name;
    }


    @JsonSetter("name")  // @JsonSetter是@JsonProperty的替代方法—它将方法标记为setter方法
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