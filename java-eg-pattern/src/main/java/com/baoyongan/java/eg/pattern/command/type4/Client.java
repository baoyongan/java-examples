package com.baoyongan.java.eg.pattern.command.type4;

// 使用泛型减少 Command 子类。
public class Client {

    public static void main(String[] args) {
        // 具体的操作
        // 包装操作的具体命令
        ConcreteCommand cc=new ConcreteCommand((s,t)->{
            System.out.println(s+" world");
            System.out.println(t+"号");
        },"hello",3);
        // 定义命令执行器
        Invoker invoker=new Invoker();
        // 调用命令
        invoker.storeCommand(cc);
        // 命令执行
        invoker.invoke();
    }
}
