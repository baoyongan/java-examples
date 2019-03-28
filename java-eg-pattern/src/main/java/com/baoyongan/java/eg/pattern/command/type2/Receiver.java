package com.baoyongan.java.eg.pattern.command.type2;

// 业务接受服务
public class Receiver {
    public Action action() {
        /*return new Action() {
            public void action() {
                System.out.println("do somethings");
            }
        };*/
        return ()-> System.out.println("do somethings");
    }
}
