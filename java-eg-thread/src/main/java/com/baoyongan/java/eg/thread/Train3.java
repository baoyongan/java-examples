package com.baoyongan.java.eg.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 1到100，用三个线程按顺序打印，
 * 线程1 打印只能被3整除的
 * 线程2 打印只能被5整除的
 * 线程3 打印所有其他的
 * 下面的方法：相对于之前的两个方法会执行更快，相对与信号量许可，可以减少无效的运行。
 */
public class Train3 {

    private CountDownLatch countDownLatchStart = new CountDownLatch(1); // 计时用
    private CountDownLatch countDownLatchEnd = new CountDownLatch(3);   // 计时用
    private AtomicInteger ai = new AtomicInteger(0);
    Thread t1 = null;
    Thread t2 = null;
    Thread t3 = null;

    public void start() throws InterruptedException {
        t1.start();
        t2.start();
        t3.start();
        long s = System.nanoTime();
        countDownLatchStart.countDown();// 开始执行
        countDownLatchEnd.await(); // 等待结束
        long e = System.nanoTime();
        long o = e - s;
        System.out.println("时间（纳秒）：" + o);
        System.out.println("时间（毫秒）：" + o / (1000000));
    }

    public Train3() {
        this.t1 = new Thread(() -> {
            try {
                // 等待闭锁状态
                countDownLatchStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                if (ai.get() > 100) {
                    break;
                }
                int i = ai.get();
                while (i % 3 == 0 && i % 5 != 0) {
                    System.out.println("线程Prin3打印：" + i);
                    i = ai.incrementAndGet();
                    if (i > 100) {
                        break;
                    }
                }
                LockSupport.unpark(t2);
                LockSupport.unpark(t3);
                LockSupport.park();
            }
            countDownLatchEnd.countDown();
        });
        this.t2 = new Thread(() -> {
            try {
                // 等待闭锁状态
                countDownLatchStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                if (ai.get() > 100) {
                    break;
                }
                int i = ai.get();
                while (i % 3 != 0 && i % 5 == 0) {
                    System.out.println("线程Prin5打印：" + i);
                    i = ai.incrementAndGet();
                    if (i > 100) {
                        break;
                    }
                }
                LockSupport.unpark(t1);
                LockSupport.unpark(t3);
                LockSupport.park();
            }
            countDownLatchEnd.countDown();
        });
        this.t3 = new Thread(() -> {
            try {
                // 等待闭锁状态
                countDownLatchStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                if (ai.get() > 100) {
                    break;
                }
                int i = ai.get();
                while ((i % 3 != 0 && i % 5 != 0) || (i % 3 == 0 && i % 5 == 0)) {
                    System.out.println("线程PrinO打印：" + i);
                    i = ai.incrementAndGet();
                    if (i > 100) {
                        break;
                    }
                }
                LockSupport.unpark(t1);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            countDownLatchEnd.countDown();
        });


    }

    public static void main(String[] args) throws InterruptedException {
        Train3 train = new Train3();
        train.start();
    }
}
