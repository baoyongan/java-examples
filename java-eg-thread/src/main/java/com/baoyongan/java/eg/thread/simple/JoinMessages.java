package com.baoyongan.java.eg.thread.simple;

import static java.lang.Thread.sleep;

public class JoinMessages {

    public static void main(String args[])
            throws InterruptedException {

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    System.out.println("thread-2-count-"+i);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                System.out.println("thread2-over");
            }
        });

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <5 ; i++) {
                    System.out.println("thread-1-count-"+i);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                try {
                    // 暂停当前线程，执行t2，等待t2终止
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1-over");
            }
        });

        t.start();
        t2.start();
        // 当前线程是主线程，会暂停当前线程，等待t线程终止，可以加参数设置最长等待的时间。
        t.join();
        System.out.println("执行完成");
    }
}
