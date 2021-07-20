package com.baoyongan.java.eg.thread.state_ch;

public class StateSleep {

    public static void main(String[] args) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }
}
