package com.baoyongan.java.eg.thread.concurrent.locks_ch;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    public static void main(String[] args) {
        // 重入锁
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 100; i++) {
            String threadName = "lockThread" + i;
            new Thread(() -> {
                lock.lock(); // 获取锁
                try {
                    System.out.println(threadName + "processing......");
                } finally {
                    lock.unlock(); // 释放锁
                }
            }, threadName).start();
        }
    }
}
