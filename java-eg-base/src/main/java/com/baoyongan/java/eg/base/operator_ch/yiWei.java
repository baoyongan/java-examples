package com.baoyongan.java.eg.base.operator_ch;

public class yiWei {

    public static void main(String[] args) {
        int number = 11;
        //原始数二进制
        printInfo(number);
        //右移一位
        number = number >> 1;
        printInfo(number);
        //左移一位
        number = number << 1;
        printInfo(number);


        testOther();
    }

    private static void testOther() {
        System.out.println("=============================");
        int h=162132412;
        printInfo(h);
        int t=h >>> 16;
        printInfo(t);
        int d = h ^ t;
        printInfo(d);
    }

    /**
     * 26      * 输出一个int的二进制数
     * 27      * @param num
     * 28
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }


}
