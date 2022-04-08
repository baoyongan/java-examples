package com.baoyongan.java.eg.base.collection_ch;

import java.util.*;

public class MapTest {

    public static void main(String[] args) {

        Map<String,String> a=new HashMap();
        for (int i = 0; i < 1; i++) {
            a.put("shashahaha"+i,i+"");
        }

        for (Map.Entry<String,String> entry: a.entrySet()) {
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }

        System.out.println("注意null 的情况===================================");

        Map<String,Object> b=new HashMap<>();
        b.put("22",null);
        b.put("11","11");

        System.out.println(b.get("22"));
        System.out.println(b.get("33"));


        HashSet<String> ss=new HashSet<String>();


        String[] al=new String[5];
        al[0]="2";
        System.out.println(al);
        int index=0;
        do{}while(index<al.length && al[index++]==null);
        System.out.println(index);


        Hashtable hashtable=new Hashtable();

    }
}
