package com.baoyongan.java.eg.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁 同步工具类，可以延迟线程的进度直到其到达终止状态
 */
public class CountDownLatchTest {



    public static void main(String[] args) throws InterruptedException {
        //
       long mills= timeTasks(5,new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(mills);
    }

    private static long timeTasks(int i, Runnable runnable) throws InterruptedException {
        CountDownLatch startGate=new CountDownLatch(1);
        CountDownLatch endGate=new CountDownLatch(i);

        for (int j = 0; j <i ; j++) {
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await(); // 等待闭锁状态
                        try{
                            // 执行工作
                            runnable.run();
                        }finally {
                            // 释放一个闭锁--  放在finally还是放在 try 块里面由业务决定
                            endGate.countDown(); //
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        long start=System.nanoTime();
        // 所有线程都阻塞在   startGate.await()
        startGate.countDown();  // 所有线程放行
        endGate.await(); // 阻塞本线程，等待所有线程结束
        long end=System.nanoTime();
        return end-start;
    }
}
