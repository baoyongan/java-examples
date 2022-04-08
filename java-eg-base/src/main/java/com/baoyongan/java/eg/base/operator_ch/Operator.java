package com.baoyongan.java.eg.base.operator_ch;

/**
 * Created by bqct_bya on 2017/10/11.
 */
public class Operator {

    public static void main(String[] args) {
        int b=3;
        int a=1+b++;
        System.out.println(a);


        int i=0;

        for (; i < 10; i++) {
            System.out.println(i);
        }

        System.out.println("end i="+i);

        int a1=10;
        int b1=1;
        int c=a1^b1;
        System.out.println("二进制结果："+Integer.toBinaryString(c));
    }

}
