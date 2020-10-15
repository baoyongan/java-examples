package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({ "id" }) // 该注解是类级别，标记一个或一组可忽略的属性
public class BeanWithIgnore {
    public int id;
    public String name;

    @JsonIgnore  // 标记一个可忽略的属性
    public String nick;

    public BeanWithIgnore(int id, String name) {
        this.id = id;
        this.name = name;
    }
}