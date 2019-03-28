package com.baoyongan.java.eg.pattern.command.type1;

// 直接注入 Receiver 对象，Command 决定调用哪个方法。
public class Client {


    public static void main(String[] args) {
        // 具体的操作
        Receiver receiver=new Receiver();
        // 包装操作的具体命令
        ConcreteCommand cc=new ConcreteCommand(receiver);
        // 定义命令执行器
        Invoker invoker=new Invoker();
        // 调用命令
        invoker.storeCommand(cc);
        // 命令执行
        invoker.invoke();
    }
}
