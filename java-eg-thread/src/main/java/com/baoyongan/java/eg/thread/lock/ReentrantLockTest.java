package com.baoyongan.java.eg.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    public static void main(String[] args) {

        ReentrantLock lock=new ReentrantLock(true);
        for (int i = 0; i < 100; i++) {
            String threadName="lockThread"+i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock(); // 获取锁
                    try{
                        System.out.println(threadName+"processing......");
                    }finally {
                        lock.unlock(); // 释放锁
                    }
                }
            },threadName).start();
        }

    }
}
