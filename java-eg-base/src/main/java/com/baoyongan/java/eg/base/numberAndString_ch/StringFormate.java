package com.baoyongan.java.eg.base.numberAndString_ch;

import java.util.UUID;

public class StringFormate {

    public static void main(String[] args) {

        String a="我是你大爷";
        System.out.println(a.hashCode());

        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString().toUpperCase();
            String endChar = uuid.substring(uuid.length() - 1);
            int i10value = Integer.parseInt(endChar, 16);
            System.out.println(endChar + "======" + i10value +"====="+String.format("%02d", i10value));
        }
    }
}
