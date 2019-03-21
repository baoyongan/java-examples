package com.baoyongan.java.eg.thread.synch;

public class VisivilityTest
{
    private static int i;
    private static boolean flag;

    static class ReaderThread extends Thread{

        public void run(){
            while (!flag)
                Thread.yield();
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        // 下面的赋值 可能被 重排序
        i=45;
        flag=true;
    }
}
