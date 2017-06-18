package com.bya.springmvc4.domain;

/**
 * Created by bao on 2017/6/18.
 */
public class DemoObj {
    private Long id;
    private String name;

//    jackson对对象和json转换的时候需要此空的构造器
    public DemoObj() {
    }

    public DemoObj(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
