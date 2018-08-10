package com.baoyongan.java.eg.base.operator_ch;

public class yiWei {

    public static void main(String[] args) {
        int number = 10;
        //原始数二进制
        printInfo(number);
        number = number << 1;
        //左移一位
        printInfo(number);
        number = number >> 1;
        //右移一位
        printInfo(number);
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
