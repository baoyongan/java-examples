package com.baoyongan.java.eg.thread.state_ch;

public class StateWaitX {

    private Object lock=new Object();
    private Thread b=null;
    private Thread c=null;
    private Thread e=null;
    private Thread f=null;


    public void waitx(long x){
        new Thread(()->{
            synchronized (lock){
                System.out.printf("A 获取了 lock\n");
                System.out.printf("A wait( %d s)\n",x/1000);
                long s=System.currentTimeMillis();
                try {
                    lock.wait(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long e=System.currentTimeMillis();
                System.out.printf("%d ms后, A 继续执行\n",e-s);
            }
        },"A").start();
    }

    public void goB(long x){
        b=new Thread(()->{
            synchronized (lock){
                System.out.printf("B 获取了 lock\n");
                System.out.printf("B sleep %d s\n",x/1000);
                try {
                    Thread.sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("B 结束了\n");
            }
        },"B");
    }

    public void goC(long x){
        c=new Thread(()->{
            synchronized (lock){
                System.out.printf("C 获取了 lock\n");
                System.out.printf("C sleep %d s\n",x/1000);
                try {
                    Thread.sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("C 结束了\n");
            }
        },"C");
    }

    public void goE(long x){
        e=new Thread(()->{
            synchronized (lock){
                System.out.printf("E 获取了 lock\n");
                System.out.printf("E sleep %d s\n",x/1000);
                try {
                    Thread.sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("E 结束了\n");
            }
        },"E");
    }

    public void goF(long x){
        f=new Thread(()->{
            synchronized (lock){
                System.out.printf("F 获取了 lock\n");
                System.out.printf("F sleep %d s\n",x/1000);
                try {
                    Thread.sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("F 结束了\n");
            }
        },"F");
    }

    public void start(){
        b.start();
        f.start();
        c.start();
        e.start();
    }



    public static void main(String[] args) throws InterruptedException {
        StateWaitX s=new StateWaitX();
        s.waitx(5000);
        s.goB(5000);
        s.goC(5000);
        s.goE(5000);
        s.goF(5000);
        s.start();
    }
}
