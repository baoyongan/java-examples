package com.baoyongan.java.eg.thread.state_ch;

public class StateJoin {

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行中");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        }, "Thread-A");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行中");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        }, "Thread-B");

        a.start();
        b.start();
        System.out.println("主线程，执行B.join");
        b.join(); // 内部实现 是b 的同步方法join(0),内部调用了b.wait(0); 也就是调用的线程会被处于waiting状态，直到b线程执行完成，调用线程才被唤醒继续执行。
//        线程b执行完成会执行this.notifyAll(),在源码中找不到出处，但在Thread 的Native本地方法的实现中可以看到。

        // 注意：无法保证runable 状态下多线程的执行顺序，除非 b.start(); b.join(); a.start(); a.join();
        System.out.println("over");
    }
}
