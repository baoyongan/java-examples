package com.baoyongan.java.eg.thread.concurrent;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ExchangerTest {

    public static void main(String[] args) {
        FillAndEmpty fae=new FillAndEmpty();
        fae.start();
    }
}

class FillAndEmpty {
    Exchanger<String> exchanger = new Exchanger<String>();
    String initialEmpty = new String("");
    String initialFull = new String("");
    int length=4;
    char[] codes={'A','B','C','D','E','a','b','c','d','e'};

    class FillingLoop implements Runnable {
        Random random=new Random();
        public void run() {
            String currentBuffer = initialEmpty;
            try {
                while (currentBuffer != null) {
                    currentBuffer=addToBuffer(currentBuffer);
                    if (isFull(currentBuffer)){
                        System.out.println("交换前EmptyBuffer："+currentBuffer.hashCode()+"内容："+currentBuffer);
                        currentBuffer = exchanger.exchange(currentBuffer);
                        System.out.println("交换后EmptyBuffer："+currentBuffer.hashCode()+"内容："+currentBuffer);
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private boolean isFull(String currentBuffer) {
            if(currentBuffer.length()==length){
                return true;
            }
            return false;
        }

        private String addToBuffer(String currentBuffer) {
            int i = random.nextInt(8);
            char ch=codes[i];
            currentBuffer=currentBuffer+ch;
            System.out.println("添加后的内容"+currentBuffer);
            return currentBuffer;
        }
    }

    class EmptyingLoop implements Runnable {
        Random r=new Random();
        public void run() {
            String currentBuffer = initialFull;
            try {
                while (currentBuffer != null) {
                    currentBuffer=takeFromBuffer(currentBuffer);
                    if (isEmpty(currentBuffer)) {
                        System.out.println("交换前FullBuffer：" + currentBuffer.hashCode() + "内容：" + currentBuffer);
                        currentBuffer = exchanger.exchange(currentBuffer);
                        System.out.println("交换后FullBuffer：" + currentBuffer.hashCode() + "内容：" + currentBuffer);
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private boolean isEmpty(String currentBuffer) {
            return currentBuffer.isEmpty();
        }

        private String takeFromBuffer(String currentBuffer) {
            System.out.println("取出的内容："+currentBuffer);
            currentBuffer="";
            return currentBuffer;
        }
    }

    void start() {
        new Thread(new FillingLoop()).start();
        new Thread(new EmptyingLoop()).start();
    }
}
