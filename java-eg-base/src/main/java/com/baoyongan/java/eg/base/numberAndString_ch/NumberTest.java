package com.baoyongan.java.eg.base.numberAndString_ch;

import java.math.BigInteger;

/**
 * Created by bqct_bya on 2017/10/18.
 */
public class NumberTest {

    public static void main(String[] args) {

        Short i=new Short((short) -1);
        System.out.println(Short.toUnsignedInt(i));


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
