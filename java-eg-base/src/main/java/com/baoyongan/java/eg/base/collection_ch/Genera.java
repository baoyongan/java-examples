package com.baoyongan.java.eg.base.collection_ch;

import java.util.*;

public class Genera {
    public static void main(String[] args) {

        Map m=new HashMap();
        m.put("k","v");
        System.out.println(m);

        List<String> ss=new LinkedList<>();
        Collections.unmodifiableList(ss);


    }
}
