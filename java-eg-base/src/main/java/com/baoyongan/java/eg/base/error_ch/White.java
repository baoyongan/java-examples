package com.baoyongan.java.eg.base.error_ch;

import javax.naming.ldap.StartTlsRequest;

public class White {
    static int ENT=Integer.MAX_VALUE;
    static int START=ENT-10;
    public static void main(String[] args) throws InterruptedException {

        int count=0;
        System.out.println(START);
        System.out.println(ENT);
        for (int i = START; i <=ENT ; System.out.println(i++)) {   // 数字越界使校验条件失效  END++ 就越界了。变成负数
            count++;
            Thread.sleep(1000);
        }
        System.out.println(count);
    }
}
