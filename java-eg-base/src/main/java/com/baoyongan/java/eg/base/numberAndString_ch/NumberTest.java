package com.baoyongan.java.eg.base.numberAndString_ch;

import java.math.BigInteger;

/**
 * Created by bqct_bya on 2017/10/18.
 */
public class NumberTest {

    public static void main(String[] args) {

        Short i=new Short((short) -1);
        System.out.println(Short.toUnsignedInt(i));

        // int 值溢出，不会报错，因为符号位变成1，所以变成了负值
        int max=Integer.MAX_VALUE;
        int sm=max+1;
        System.out.println("测试溢出情况"+sm);
        // 典型的测试
        int lowest=-2147483648;
        int low=Integer.MIN_VALUE;
        int abs = Math.abs(lowest);
        System.out.printf("int 的最小值：%d, %d,使用math.abs 取绝对值返回：%d,溢出情况 \n",lowest,low,abs);
        // 负数的取余数运算注意项
        // (a/b)*b+a%b=a
        System.out.printf("-14/3= %d \n",-14/3);
        System.out.printf("14/-3= %d \n",14/-3);
        System.out.printf("-14%%3= %d \n",-14%3);
        System.out.printf("14%%-3= %d \n",14%-3);

        BigInteger bigInteger=new BigInteger("1212132132132212");

//        String s = Integer.toHexString(1);
//        System.out.println(s);

//        System.out.println(Integer.parseInt());
//        int a=0x0f;
        int a=0x7FFFFFFF;
        System.out.println(a);
        System.out.println(Integer.parseInt("F", 16));
        System.out.println(Integer.toHexString(15));
        String s = Integer.toBinaryString(a);
        System.out.println(s.length());
//        System.out.println(Integer.toBinaryString(a));


    }
}
