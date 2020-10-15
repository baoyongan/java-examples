package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class ExtendableBean {
    public String name;
    private Map<String, String> properties;

    public ExtendableBean() {
    }

    public ExtendableBean(String name) {
        this.name=name;
    }



    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter // @JsonAnySetter允许我们灵活地使用映射作为标准属性。在反序列化时，JSON的属性将被添加到映射中。
    public void add(String key, String value) {
        synchronized (this){
            if (null==properties)
                properties=new HashMap<>();
            properties.put(key, value);
        }
    }

    /*public void add(String key, String value) {
        synchronized (this){
            if (null==properties)
                properties=new HashMap<>();
            properties.put(key, value);
        }
    }*/

    @Override
    public String toString() {
        return "ExtendableBean{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}