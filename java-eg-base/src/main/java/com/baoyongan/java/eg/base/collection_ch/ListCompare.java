package com.baoyongan.java.eg.base.collection_ch;

import java.util.Arrays;
import java.util.Comparator;

public class ListCompare {

    public static void main(String[] args) {

        String[] sss=new String[]{"access_token","app_key","method","timestamp","v","360buy_param_json","AppSecure"};
        Arrays.sort(sss, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.asList(sss));


        int s="360buy_param_json".compareTo("access_token");
        System.out.println(s);
        char a='a';
        char b='b';
        char c='1';
        char d='A';
        System.out.println((int)a);
        System.out.println((int)b);
        System.out.println((int)c);
        System.out.println((int)d);

        System.out.println("aaa".compareTo("AAA"));
    }
}
