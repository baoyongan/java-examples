package com.baoyongan.java.eg.thread.concurrent.locks_ch;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        // 读写锁
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Thread t1=new Thread(()->{
            System.out.println("readThread1 请求lock");
            readLock.lock();
            try{
                System.out.println("readThread1 begin sleep 3s");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("readThread1 over");
            }finally {
                readLock.unlock();
            }
        },"Thread-T1");

        Thread t3=new Thread(()->{
            System.out.println("readThread3 请求lock");
            readLock.lock();
            try{
                System.out.println("readThread3 begin sleep 3s");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("readThread3 over");
            }finally {
                readLock.unlock();
            }
        },"Thread-T3");

        Thread t2= new Thread(()->{
            System.out.println("writeThread 请求lock");
            writeLock.lock();
            try {
                System.out.println("writeThread begin");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("writeThread over");
            }finally {
                writeLock.unlock();
            }
        },"Thread-T2");

        t2.start();
        t1.start();
        t3.start();
    }
}
