package com.baoyongan.java.eg.thread.concurrent.locks_ch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 锁的Condition接口解决，等待和唤醒的问题，底层依赖是LockSupport.park 和 unpark
 */
public class ConditionTest {

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        // 创建该锁的 condition 对象
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                try {
                    System.out.println(Thread.currentThread().getName()+"进入await....");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T1");

        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                try {
                    System.out.println(Thread.currentThread().getName()+"进入await....");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T2");

        Thread t3 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                try {
                    System.out.println(Thread.currentThread().getName()+"进入await....");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T3");

        Thread t4 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                System.out.println(Thread.currentThread().getName()+"signal 一个线程");
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"继续执行...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T4");

        Thread t5 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                try {
                    System.out.println(Thread.currentThread().getName()+"进入await....");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T5");

        Thread t6 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                System.out.println(Thread.currentThread().getName()+"signal 一个线程");
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"继续执行...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T6");

        Thread t7 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"running...");
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                System.out.println(Thread.currentThread().getName()+"signalAll 所有等待的线程");
                condition.signalAll();
                System.out.println(Thread.currentThread().getName()+"继续执行...");
                System.out.println(Thread.currentThread().getName()+"over...");
            }finally {
                lock.unlock();
            }
        },"T7");

        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start();
        t5.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t6.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t7.start();
    }
}
