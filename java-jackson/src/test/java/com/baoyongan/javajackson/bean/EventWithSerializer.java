package com.baoyongan.javajackson.bean;

import com.baoyongan.javajackson.deserializer.CustomDateDeserializer;
import com.baoyongan.javajackson.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class EventWithSerializer {
    public String name;
 
    @JsonSerialize(using = CustomDateSerializer.class) // 使用自定义的序列化器进行序列化
    @JsonDeserialize(using = CustomDateDeserializer.class) // 表示使用了自定义反序列化器
    public Date eventDate;

    public EventWithSerializer() {
    }

    public EventWithSerializer(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "EventWithSerializer{" +
                "name='" + name + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}