package com.baoyongan.java.eg.pattern.command.type3;

// 具体的commond
public class ConcreteCommand implements Command {

    private Action action;
    private String sr;

    public ConcreteCommand(Action action, String sr) {
        this.action = action;
        this.sr = sr;
    }

    public ConcreteCommand(Action receiver) {
        this.action = receiver;
    }

    public void execute() {
        action.action(sr);
    }
}
