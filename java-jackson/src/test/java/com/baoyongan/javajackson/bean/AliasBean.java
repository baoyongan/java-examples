package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class AliasBean {
    @JsonAlias({ "fName", "f_name" }) // @JsonAlias在反序列化期间为属性定义一个或多个替代名称
    private String firstName;   
    private String lastName;


}