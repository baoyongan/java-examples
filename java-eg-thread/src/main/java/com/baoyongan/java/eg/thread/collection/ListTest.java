package com.baoyongan.java.eg.thread.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        // 当迭代操作远多于修改操作时候适合
    }
}
