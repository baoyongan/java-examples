package com.baoyongan.java.eg.pattern.command.type2;

// 具体的commond
public class ConcreteCommand implements Command {

    private Action action;

    public ConcreteCommand(Action receiver) {
        this.action = receiver;
    }

    public void execute() {
        action.action();
    }
}
