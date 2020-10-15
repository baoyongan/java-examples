package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user") // 指定包装器的名称 默认情况包装器的名称将是类的名称
public class UserWithRoot{
    public int id;
    public String name;

    public UserWithRoot(int id, String name) {
        this.id = id;
        this.name = name;
    }
}