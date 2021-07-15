package com.baoyongan.java.eg.base.collection_ch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {

        Map<String,String> a=new HashMap();

        a.put("22","11");
        a.put("223","12");

        for (Map.Entry<String,String> entry: a.entrySet()) {
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }

    }
}
