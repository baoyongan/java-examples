package com.baoyongan.java.eg.thread.concurrent.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static void main(String[] args) throws InterruptedException {
        // 整个实例加锁（synchronized）
        List<String> list= Collections.synchronizedList(new ArrayList<String>());
        // 大量的CAS操作和分段加锁。


        ConcurrentHashMap<String,Integer> a=new ConcurrentHashMap();

        int s=a.computeIfAbsent("ba",k->{
            return (int)k.charAt(0);
        });
        System.out.println(s);
        int ss=a.computeIfAbsent("cc",k->{
            return (int)k.charAt(0);
        });
        System.out.println(ss);

        int sss=a.computeIfAbsent("ba",k->{
            return (int)k.charAt(0);
        });
        System.out.println(sss);
        a.forEach((k,v)->{
            System.out.printf("key : %s, Value: %d\n",k,v);
        });

        ConcurrentHashMap<String, AtomicInteger> as=new ConcurrentHashMap();
        new Thread(()->{
            AtomicInteger v=as.computeIfAbsent("a",k-> new AtomicInteger(0));
            v.incrementAndGet();
            System.out.println(v.toString());
        }).start();
        new Thread(()->{
            AtomicInteger v=as.computeIfAbsent("a",k-> new AtomicInteger(0));
            v.incrementAndGet();
            System.out.println(v.toString());
        }).start();
        new Thread(()->{
            AtomicInteger v=as.computeIfAbsent("a",k-> new AtomicInteger(0));
            v.incrementAndGet();
            System.out.println(v.toString());
        }).start();

        Thread.sleep(2000);
        as.forEach((k,v)->{
            System.out.println("key : "+k+", Value: "+v);
        });
    }
}
