package com.baoyongan.java.eg.thread.concurrent.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发集合
 * ConcurrentHashMap
 * ConcurrentSkipListMap
 * ConcurrentSkipListSet
 * CopyOnWriteArrayList
 * CopyOnWriteArraySet
 *
 * 同步容器（所有操作都是Synchronized）
 * HashTable
 * Vector
 * synchronizedList
 * synchronizedSet
 * ...
 */
public class CollectionsTest
{
    public static void main(String[] args) {
        // 整个实例加锁（synchronized）
        List<String> list= Collections.synchronizedList(new ArrayList<String>());
        // 大量的CAS操作和分段加锁。
        ConcurrentHashMap a=new ConcurrentHashMap();
    }
}
