package com.baoyongan.java.eg.base.json_ch;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class MapperBean implements Serializable {
    @Override
    public String toString() {
        return "MapperBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @JSONField()
    private String name;
    private Integer age;
    private String addr;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
