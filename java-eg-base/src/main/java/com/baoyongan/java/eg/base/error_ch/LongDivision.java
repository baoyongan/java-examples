package com.baoyongan.java.eg.base.error_ch;

public class LongDivision {
    static long X= 24*60*60*1000*1000; // * 左右两边都是int 所有最后就是越界了
    static long Y= 24*60*60*1000;
    public static void main(String[] args) {
        System.out.println(X/Y);
    }
}
