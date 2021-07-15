package com.baoyongan.java.eg.base.class_ch.enums;

public enum  MyEnum {
    ZERO(1),
    FIRST(2);


    private int code;

    MyEnum(int i) {
        this.code=i;
    }

    public void loop(){
        System.out.println(this.ordinal());
    }

    public static void main(String[] args) {
        MyEnum.ZERO.loop();
        MyEnum.FIRST.loop();
    }
}
