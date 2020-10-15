package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

@Data
public class UnwrappedUser {
    public int id;
 
    @JsonUnwrapped  // 定义了在序列化/反序列化时应该被解包装/扁平化的值
    public Name name;
 
    public static class Name {
        public String firstName;
        public String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public UnwrappedUser(int id, Name name) {
        this.id = id;
        this.name = name;
    }
}