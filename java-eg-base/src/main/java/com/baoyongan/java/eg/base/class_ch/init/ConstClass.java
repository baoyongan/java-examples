package com.baoyongan.java.eg.base.class_ch.init;

public class ConstClass{
    static {
        System.out.println("ConstClass init");
    }
    
    public static final String HELLOWORLD="hello world";
}