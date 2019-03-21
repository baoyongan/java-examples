package com.baoyongan.java.eg.thread.interrupt;

public class Rupt001 {

    final Object obj=new Object();

    public static void main(String[] args) throws InterruptedException {
        Rupt001 r=new Rupt001();
        Work1 w1=r.new Work1();
        Work2 w2=r.new Work2();
        Work3 w3=r.new Work3();

        w1.start();
        w2.start();
        w3.start();
        Thread.sleep(100);
        w1.interrupt();
        Thread.sleep(100);
        w2.interrupt();
        Thread.sleep(100);
        w3.interrupt();

    }

    class Work1 extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupt");
                // ignor e
            }
        }
    }

    class Work2 extends Thread{

        @Override
        public void run() {
            synchronized (obj){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("wait interrupt");
                }
            }
        }
    }

    class Work3 extends Thread {

        @Override
        public void run() {
            synchronized (obj) {
                long i = 0;
                try{
                    while (canGo()) {
                        i++;
                        if (i % 100 == 0)
                            System.out.println("cycle:" + i);
                    }
                }catch (InterruptedException e){
                    System.out.println("I was Interrupted!");
                }

            }
        }

        private boolean canGo() throws InterruptedException {
            if (Thread.currentThread().isInterrupted()){ // 会清除 中断状态，并且返回之前的状态
                throw new InterruptedException("some operation interrupt");
            }
            return true;
        }
    }


}
