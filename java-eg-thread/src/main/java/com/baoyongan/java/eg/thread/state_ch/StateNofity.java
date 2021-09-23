package com.baoyongan.java.eg.thread.state_ch;

/**
 * 理解 notify() 和 notifyAll() 的区别
 * 为相同对象锁等待的线程在 锁池中，因相同锁而wait()的线程在 等待池中，notify动作就是随机将等待池中一个线程放到锁池中，notifyALl就是将等待池中的所有线程放到锁池中。
 */
public class StateNofity {
    private Object lock = new Object();

    public void runTheadA() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从lock对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "释放锁,进入该锁的等待池等待");
                        lock.wait();
                        // 再次获得锁继续执行
                        System.out.println(Thread.currentThread().getName() + "再次获得锁,继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, "Thread-A").start();
    }

    public void doNotifyAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    // 释放锁
                    System.out.println(Thread.currentThread().getName() + "唤醒该对象的等待池中所有线程进入锁池竞争机会");
                    lock.notifyAll();
                    System.out.println(Thread.currentThread().getName() + "执行结束");
                }
            }
        }, "Thread-H").start();
    }

    public void runTheadF() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "释放锁,进入该锁的等待池等待");
                        lock.wait();
                        // 再次获得锁继续执行
                        System.out.println(Thread.currentThread().getName() + "再次获得锁，继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, "Thread-F").start();
    }

    public void runTheadG() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "释放锁,进入该锁的等待池等待");
                        lock.wait();
                        // 再次获得锁继续执行
                        System.out.println(Thread.currentThread().getName() + "再次获得锁，继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, "Thread-G").start();
    }

    public void runTheadB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "释放锁,进入该锁的等待池等待");
                        lock.wait();
                        // 再次获得锁继续执行
                        System.out.println(Thread.currentThread().getName() + "再次获得锁，继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread-B").start();
    }

    public void runTheadC() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "释放锁,进入该锁的等待池等待");
                        lock.wait();
                        // 再次获得锁继续执行
                        System.out.println(Thread.currentThread().getName() + "再次获得锁，继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread-C").start();
    }

    public void runTheadD() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "随机唤醒一个该对象的等待池中线程进入锁池竞争机会");
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + "唤醒了别人，锁区域内我还继续执行完成再释放锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName() + "又执行了10秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "唤醒了别人，锁外区域内我还继续执行");
            }
        }, "Thread-D").start();
    }

    public void runTheadE() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行中");
                // 竞争lock
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "从对象的锁池冲获得锁");
                    try {
                        // 当前线程睡3秒
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "持有锁等待3秒");
                        // 释放锁
                        System.out.println(Thread.currentThread().getName() + "随机唤醒一个该对象的等待池中线程进入锁池竞争机会");
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + "唤醒了别人，锁区域内我还继续执行完成再释放锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName() + "又执行了10秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "唤醒了别人，锁外区域内我还继续执行");
            }
        }, "Thread-E").start();
    }

    public static void main(String[] args) throws InterruptedException {
        StateNofity state = new StateNofity();
        state.runTheadA();
        state.runTheadB();
        state.runTheadC();
        state.runTheadD();
        state.runTheadE();
        state.runTheadF();
        state.runTheadG();
        Thread.sleep(30000);
        state.doNotifyAll();
    }
}
