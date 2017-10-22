package com.baoyongan.java.eg.thread;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 计数信号量
 * 从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * Created by bqct_bya on 2017/10/11.
 */
public class SemaphoreEg {
 private static final int MAX_AVAILABLE = 1;
 // private final Semaphore available=new Semaphore(MAX_AVAILABLE,true);

 public static void main(String[] args) {
  final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
  System.out.println(available.availablePermits());
  Thread thread1 = new Thread(new Runnable() {
   @Override
   public void run() {
    try {
     available.acquire();
     System.out.println("get thread111111111111");
     available.release();
     System.out.println("release thread111111111111");
    } catch (InterruptedException e) {
     e.printStackTrace();
    }
   }
  }, "thread111111111111");

  Thread thread2 = new Thread(new Runnable() {
   @Override
   public void run() {
    try {
     available.acquire();
     System.out.println("get thread222222222222");
     available.release();
    } catch (InterruptedException e) {
     e.printStackTrace();
    }
   }
  }, "thread222222222222");

  Thread thread3 = new Thread(new Runnable() {
   @Override
   public void run() {
    try {
     available.acquire();
     System.out.println("get thread33333333333");
     available.release();
    } catch (InterruptedException e) {
     e.printStackTrace();
    }
   }
  }, "thread33333333333");
  thread1.start();
  thread2.start();
  thread3.start();
  available.release(); // release 会一直增加
  System.out.println(available.availablePermits());
  System.out.println("over");

 }

}
