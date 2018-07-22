package com.baoyongan.java.eg.thread.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTest
{
    public static void main(String[] args) {
        List<String> list= Collections.synchronizedList(new ArrayList<String>());
    }
}
