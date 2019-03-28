package com.baoyongan.java.eg.pattern.command.type1;

// 具体的commond
public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        // Command 决定调用哪个方法。
        receiver.action();
    }
}
