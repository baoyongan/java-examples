package com.baoyongan.java.eg.thread.concurrent.queue;

import java.util.concurrent.Semaphore;

/**
 * 经典同步队列实现-采用三个信号量
 * 在多核机器上，这种方法的同步代价仍然较高，操作系统调度器需要上千个时间片来阻塞或唤醒线程，而上面的实现即使在生产者put()时已经有一个消费者在等待的情况下，阻塞和唤醒的调用仍然需要。
 * @param <E>
 */
public class SemaphoreSynchronousQueue<E> {
    E item=null;
    Semaphore sync=new Semaphore(0); // 同步信号量
    Semaphore send=new Semaphore(1); // 写入信号量
    Semaphore recv=new Semaphore(0); // 获取信号量

    public E take() throws InterruptedException {
        // 获取信号量 拿不到会阻塞
        recv.acquire();
        System.out.println("拿到获取许可");
        E x=item;
        System.out.println("添加一个同步许可");
        // 添加一个同步信号量
        sync.release();
        System.out.println("添加一个写入许可");
        // 添加一个写入信号量
        send.release();
        return x;
    }

    public void put(E x) throws InterruptedException {
        send.acquire();// 获取写入信号量
        System.out.println("拿到写入许可");
        item=x;
        System.out.println("添加一个获取许可");
        recv.release();// 添加一个获取信号量
        sync.acquire();// 获取一个同步信号量
        System.out.println("拿到一个同步许可，确认被take");
    }

    public static void main(String[] args) {
        SemaphoreSynchronousQueue<String> q=new SemaphoreSynchronousQueue<String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始获取===");
                    System.out.println("获取==="+q.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始写入===");
                try {
                    q.put("gouzi");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("写入成功");
            }
        }).start();
    }

}
