package com.baoyongan.java.eg.thread.concurrent;

import java.util.Random;
import java.util.concurrent.*;

public class BarrierDemo2 {

    public static class Player implements Runnable {
        private final String name;
        private final CyclicBarrier barrier;

        public Player(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
                System.out.println(name + "已准备,等待其他玩家准备...");
                barrier.await();
                TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
                System.out.println(name + "已加入游戏");
            } catch (InterruptedException e) {
                System.out.println(name + "离开游戏");
            } catch (BrokenBarrierException e) {
                System.out.println(name + "离开游戏");
            }

        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("所有线程已到达栅栏点");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 0; i < 5; i++) {
            service.execute(new Player("玩家" + i, barrier));
        }
        service.shutdown();
    }
}