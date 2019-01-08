package com.baoyongan.bean;

public class FooValue {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FooValue{" +
                "name='" + name + '\'' +
                '}';
    }
}
