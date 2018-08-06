package com.baoyongan.java.eg.thread;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("@@@["+t.getName()+"] throws Uncaught Exception:"+e.getMessage());
//        e.printStackTrace();
    }
}
