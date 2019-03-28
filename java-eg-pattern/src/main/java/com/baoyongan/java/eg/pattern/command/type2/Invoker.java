package com.baoyongan.java.eg.pattern.command.type2;

public class Invoker {
    private Command command;

    public void storeCommand(Command command){
        this.command=command;
    }

    public void invoke(){
        command.execute();
    }
}
