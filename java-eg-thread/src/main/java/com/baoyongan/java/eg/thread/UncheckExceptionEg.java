package com.baoyongan.java.eg.thread;

import java.util.concurrent.*;

public class UncheckExceptionEg {

    public static void main(String[] args) {

        // 只要存在这样一个未捕获的异常的处理器，JVM 就会把线程未捕获的异常处理在这个实现里面处理。默认的是输出到System.err
        MyUncaughtExceptionHandler handler = new MyUncaughtExceptionHandler();

        Thread.currentThread().setUncaughtExceptionHandler(handler);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 1 / 0;
            }
        });
        t1.setUncaughtExceptionHandler(handler);
        t1.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 1 / 0;
            }
        }, "helloThread").start();


        ThreadPoolExecutor s1=new ThreadPoolExecutor(3,3,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                Thread t=new Thread(r);
                t.setUncaughtExceptionHandler(handler);   // 只针对execute() 方式提交的才有效
                return t;
            }
        });

        s1.execute(new Runnable() {
            @Override
            public void run() {
                int a = 1 / 0;
            }
        });

        s1.submit(new Runnable() {
            @Override
            public void run() {
                int a = 1 / 0;
            }
        });

        s1.shutdown();

        int a = 1 / 0;

    }
}
