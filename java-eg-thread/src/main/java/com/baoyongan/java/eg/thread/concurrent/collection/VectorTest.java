package com.baoyongan.java.eg.thread.concurrent.collection;

import java.util.Vector;

public class VectorTest {

    private Vector<Integer> v=new Vector<>();

    public void add10(){
            for (int i = 0; i < 10; i++) {
                v.add(i);
            }
    }

    public void remove(){
        for (int i = 0; i < v.size(); i++) {
            v.remove(i);
            i--;
        }
    }

    public void print(){
        for (int i = 0; i < v.size(); i++) {
            System.out.println(v.get(i));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        VectorTest vt=new VectorTest();
        while (true){
            vt.add10();
            Thread t=new Thread(()->{
               vt.remove();
            });
            Thread d=new Thread(()->{
                vt.print();
            });
            t.start();
            d.start();
        }

    }
}
