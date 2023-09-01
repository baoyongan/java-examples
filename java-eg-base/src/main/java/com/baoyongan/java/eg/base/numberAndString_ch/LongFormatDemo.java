package com.baoyongan.java.eg.base.numberAndString_ch;

public class LongFormatDemo {
    public static void main(String[] args) {
        String num="20251212000000";
        System.out.println(Long.parseLong(num));
        System.out.println(Long.valueOf(num));
        Double a=null;
        double b=a;  // 会报空指针
        System.out.println(a);
        System.out.println(b);
    }
}
