package com.baoyongan.java.eg.base.generic_ch;

public class Boxs<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}