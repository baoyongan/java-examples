package com.baoyongan.java.eg.base.collection_ch;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {


        Map<String,Object> ss1=new HashMap<>();
        String stust= (String) ss1.get("ststus");
        System.out.println("----------"+stust);




        List<Map<String,Integer>> dd=new ArrayList<>();
        Map<String,Integer> a1=new HashMap<>();
        a1.put("day",20211122);
        a1.put("name", 1);
        dd.add(a1);
        Map<String,Integer> a2=new HashMap<>();
        a2.put("day",20231122);
        a2.put("name", 2);
        dd.add(a2);
        Map<String,Integer> a3=new HashMap<>();
        a3.put("day",20241122);
        a3.put("name", 3);
        dd.add(a3);

        Collections.sort(dd, ((o1, o2) -> {
            return o1.get("day").compareTo(o2.get("day"));
        }));

        System.out.println(dd.toString());
        Collections.sort(dd, ((o1, o2) -> {
            return o2.get("day").compareTo(o1.get("day"));
        }));
        System.out.println(dd.toString());


        Map<String, String> a = new HashMap();
        for (int i = 0; i < 1; i++) {
            a.put("shashahaha" + i, i + "");
        }

        for (Map.Entry<String, String> entry : a.entrySet()) {
            System.out.println(entry.getKey() + "----" + entry.getValue());
        }


        Hashtable s = new Hashtable();

        ConcurrentHashMap ssd = new ConcurrentHashMap();
        System.out.println("注意null 的情况===================================");

        Map<String, Object> b = new HashMap<>();
        b.put("22", null);
        b.put("11", "11");

        System.out.println(b.get("22"));
        System.out.println(b.get("33"));


        HashSet<String> ss = new HashSet<String>();


        String[] al = new String[5];
        al[0] = "2";
        System.out.println(al);
        int index = 0;
        do {
        } while (index < al.length && al[index++] == null);
        System.out.println(index);


        Hashtable hashtable = new Hashtable();

        Map<String, Integer> d1 = Collections.singletonMap("a", 65);

    }
}
