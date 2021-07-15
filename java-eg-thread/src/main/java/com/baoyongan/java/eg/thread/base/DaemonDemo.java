package com.baoyongan.java.eg.thread.base;

import java.io.IOException;

public class DaemonDemo extends Thread {

    @Override
    public void run() {
        for (int i=0;;i=i+2){
            try {
                System.out.println("Daemon:"+i);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("被中断了，i="+i);
            }
        }
    }

    public static void main(String[] args) {
//        testOneThread(); // 单线程下测试
        testManyThread(); // 用户和守护线程都存在下测试

    }

    private static void testManyThread() {
        DaemonDemo daemonDemo =new DaemonDemo();
        daemonDemo.setDaemon(true); // true 表示守护线程，在JVM只剩下守护线程的情况下，jvm 退出；false :表示用户线程（默认值），主线程结束后，用户线程还会继续运行，jvm 存活
        daemonDemo.start();

        UserThread userThread=new UserThread();
        userThread.setDaemon(false);
        userThread.start();

        System.out.println("deamonDemo flag:"+daemonDemo.isDaemon());
        System.out.println("userThread flag:"+userThread.isDaemon());
        System.out.println("阻塞主线程，等待输入：");
        try {
            System.in.read(); // 阻塞主线程，等待接收输入，一旦用户完成输入，main 线程（主线程）结束，在有用户线程存在的情况下，守护线程不会退出也会继续执行
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("完成输入主线程结束");
    }

    private static void testOneThread() {
        DaemonDemo daemonDemo =new DaemonDemo();
        daemonDemo.setDaemon(true); // true 表示守护线程，在JVM只剩下守护线程的情况下，jvm 退出；false :表示用户线程（默认值），主线程结束后，用户线程还会继续运行，jvm 存活
        daemonDemo.start();

        System.out.println("deamonDemo flag:"+daemonDemo.isDaemon());
        System.out.println("阻塞主线程，等待输入：");
        try {
            System.in.read(); // 阻塞主线程，等待接收输入，一旦用户完成输入，main 线程（主线程）结束，守护线程自动结束
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("完成输入主线程结束");

    }

    static class UserThread extends Thread{
        @Override
        public void run() {
            for (int i = 1; ; i=i+2) {
                try {
                    System.out.println("User:"+i);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
