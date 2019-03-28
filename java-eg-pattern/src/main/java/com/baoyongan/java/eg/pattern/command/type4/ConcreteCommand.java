package com.baoyongan.java.eg.pattern.command.type4;

// 具体的commond
public class ConcreteCommand<T, S> implements Command {

    private Action<T, S> action;
    private T t;
    private S s;

    public ConcreteCommand(Action action, T t, S s) {
        this.action = action;
        this.t = t;
        this.s = s;
    }

    public void execute() {
        action.action(t,s);
    }
}
