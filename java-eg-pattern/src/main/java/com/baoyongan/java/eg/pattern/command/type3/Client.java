package com.baoyongan.java.eg.pattern.command.type3;

// 参数化Command 构造。
public class Client {

    public static void main(String[] args) {
        // 具体的操作
        // 包装操作的具体命令
        ConcreteCommand cc=new ConcreteCommand((s)->
                System.out.println(s+" world")
                ,"hello");
        // 定义命令执行器
        Invoker invoker=new Invoker();
        // 调用命令
        invoker.storeCommand(cc);
        // 命令执行
        invoker.invoke();
    }
}
