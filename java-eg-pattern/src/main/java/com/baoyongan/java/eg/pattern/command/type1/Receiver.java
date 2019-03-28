package com.baoyongan.java.eg.pattern.command.type1;

// 业务接受服务
public class Receiver implements Action {
    public void action() {
        System.out.println("receiver do something");
    }
}
