package com.baoyongan.java.eg.base.class_ch.init;

class Parent {
    public static int A = 1;

    static {
        A = 2;
    }
}

class Sub extends Parent {
    public static int B = A;

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}