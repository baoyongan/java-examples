package com.baoyongan.java.eg.pattern.singleton;

public enum Elvis {
    INSTANCE(1,"s");

    private final String s;
    private final Integer i;

    Elvis(int i, String s) {
        this.i=i;
        this.s=s;
    }

    public void say(){
        System.out.println("Hello "+this.s);
    }

}
