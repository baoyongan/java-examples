package com.baoyongan.java.eg.base.shutdown;

public class CloseHook {

    public static void main(String[] args) {
        // 注册钩子
        Thread hook1= new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("close hook1.....");
            }
        });

        Thread hook2= new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("close hook2.....");
            }
        });


        Runtime runtime= Runtime.getRuntime();
        runtime.addShutdownHook(hook1);
        runtime.addShutdownHook(hook2);

        System.exit(0); // 在正常关闭之前会执行钩子

    }
}
