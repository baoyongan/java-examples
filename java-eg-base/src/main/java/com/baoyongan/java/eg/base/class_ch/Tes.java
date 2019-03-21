package com.baoyongan.java.eg.base.class_ch;

public class Tes {

    public static Bicycle bicycle;

    public Tes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Tes t=Tes.this;
                t.init();
            }
        }).start();
    }

    public void init(){
        bicycle=new Bicycle(1,2,3);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Tes.bicycle);
        Tes t=new Tes();
        Thread.sleep(1000);
        System.out.println(Tes.bicycle);
        t.init();
        System.out.println(Tes.bicycle);
    }
}
