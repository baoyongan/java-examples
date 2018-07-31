package com.baoyongan.java.eg.thread.interrupt;

import java.util.concurrent.*;

public class Rupt002 {

    final Object obj=new Object();

    public static void main(String[] args) throws InterruptedException {

        FutureTask<String> task=new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("sleep interrupt");
                    // ignor e
                }
                return "sleep over";
            }
        });

        new Thread(task).start();
        try {
            task.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            //
            System.out.println("task timeout....");
            // 取消  false-如果没有启动就不要运行它
            task.cancel(true);
        }

        FutureTask<String> task100=new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {

                long i=0;
                boolean flag=true;
                while (flag) {
                    i++;
                    if (i%10000 == 0)
                        System.out.println("cycle:" + i);
                    if (i==10000000) {
                        flag=false;
                    }
                }
               return "cycle over";
            }
        });

        new Thread(task100).start();
        try {
            task100.get(10, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            //
            System.out.println("task111111111111111111111111111111111111111111111111111111111111 timeout....");
            // 取消  false-如果没有启动就不要运行它
            task100.cancel(true); // 任务必须是有取消策略的，可以取消的，否则就只是设置了一个线程的中断状态
        }

        Thread.sleep(5000);

    }

}
