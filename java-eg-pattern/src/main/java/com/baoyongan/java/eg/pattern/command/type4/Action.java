package com.baoyongan.java.eg.pattern.command.type4;

public interface Action<T,S> {
    void action(T t,S s);
}
