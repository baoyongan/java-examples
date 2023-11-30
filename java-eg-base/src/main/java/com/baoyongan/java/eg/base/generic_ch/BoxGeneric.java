package com.baoyongan.java.eg.base.generic_ch;

import java.math.BigDecimal;

/**
 * 添加了类参数的泛型化Box，特定的泛型化box,只能添加相同类型的对象。
 * @param <T>
 */
public class BoxGeneric<T> {
    // T stands for "Type"
    private T t;

    public BoxGeneric() {}

    public BoxGeneric(T t) {
        this.t = t;
    }

    public void set(T t) { this.t = t; }
    public T get() { return t; }

    /**
     * 有界类型参数
     * @param u
     * @param <U>
     */
    public <U extends Number> void inspect (U u){
        System.out.println("t = " + t.getClass().getName());
        System.out.println("u = " + u.getClass().getName());
    }

    public static void main(String[] args) {
        BoxGeneric<String> s=new BoxGeneric<>();
        s.set("ss");
        String ss=s.get();
        System.out.println(ss);
        s.inspect(1);

    }
}