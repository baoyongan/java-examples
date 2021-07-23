package com.baoyongan.java.eg.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 1到100，用三个线程按顺序打印，
 * 线程1 打印只能被3整除的
 * 线程2 打印只能被5整除的
 * 线程3 打印所有其他的
 * 下面的方法的缺点：同一线程可能频繁获得信号量，但是都不满足可以执行打印的条件。
 */
public class Train1 {

    private Semaphore semaphore = new Semaphore(1);
    private CountDownLatch countDownLatchStart=new CountDownLatch(1); // 计时用
    private CountDownLatch countDownLatchEnd=new CountDownLatch(3);   // 计时用
    private volatile int now = 0;
    Thread t1 = null;
    Thread t2 = null;
    Thread t3 = null;

    public void start() throws InterruptedException {
        t1.start();
        t2.start();
        t3.start();
        long s=System.nanoTime();
        countDownLatchStart.countDown();// 开始执行
        countDownLatchEnd.await(); // 等待结束
        long e=System.nanoTime();
        long o=e-s;
        System.out.println("时间（纳秒）："+o);
        System.out.println("时间（毫秒）："+o/(1000000));
    }

    public Train1() {
        this.t1 = new Thread(() -> {
            try {
                // 等待闭锁状态
                countDownLatchStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <= 100; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    boolean flag = true;
                    while (flag) {
                        if (semaphore.tryAcquire()) {
                            try {
                                // 是否轮到我打印了
                                if (now == i) {
                                    System.out.println("线程1打印：" + i);
                                    now++;
                                    flag = false;
                                }
                            } finally {
                                semaphore.release();
                            }
                        }
                    }
                }
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
            for (int i = 0; i <= 100; i++) {
                if (i % 3 != 0 && i % 5 == 0) {
                    boolean flag = true;
                    while (flag) {
                        if (semaphore.tryAcquire()) {
                            try {
                                // 是否轮到我打印了
                                if (now == i) {
                                    System.out.println("线程2打印：" + i);
                                    now++;
                                    flag = false;
                                }
                            } finally {
                                semaphore.release();
                            }
                        }
                    }
                }
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
            for (int i = 0; i <= 100; i++) {
                if ((i % 3 != 0 && i % 5 != 0) || (i % 3 == 0 && i % 5 == 0)) {
                    boolean flag = true;
                    while (flag) {
                        if (semaphore.tryAcquire()) {
                            try {
                                if (now == i) {
                                    System.out.println("线程3打印：" + i);
                                    now++;
                                    flag = false;
                                }
                            } finally {
                                semaphore.release();
                            }
                        }
                    }
                }
            }
            countDownLatchEnd.countDown();
        });
    }

    public static void main(String[] args) throws InterruptedException {
        Train1 train1 = new Train1();
        train1.start();
    }
}
