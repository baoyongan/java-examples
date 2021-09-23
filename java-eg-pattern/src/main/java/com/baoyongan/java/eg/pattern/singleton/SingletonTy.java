package com.baoyongan.java.eg.pattern.singleton;

public class SingletonTy {
    private SingletonTy(){
        System.out.println("初始化了");
    }
    private final static SingletonTy INSTANCE=new SingletonTy();
    public static SingletonTy getInstance(){
        return INSTANCE;
    }
    public void say(String name){
        System.out.println("Hello "+name);
    }
}
