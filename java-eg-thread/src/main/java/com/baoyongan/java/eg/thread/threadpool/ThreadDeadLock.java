package com.baoyongan.java.eg.thread.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadLock
{

    public static void main(String[] args) {
        // 必须注意任务于执行策略之间的隐形耦合
        ExecutorService executorService= Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行任务A....");
                System.out.println("任务A向线程池提交任务B并且等待结果....");

                Future future= executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("执行任务B....");
                    }
                });
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //
            }
        });

//        executorService.shutdown();
    }
}
