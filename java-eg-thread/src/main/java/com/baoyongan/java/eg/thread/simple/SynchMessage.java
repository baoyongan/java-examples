package com.baoyongan.java.eg.thread.simple;

public class SynchMessage {

    private int i;
    private int b;

    public synchronized void setii(int a) {
        this.i=a;
        setbb(a);
    }

    public synchronized void setbb(int c){
        this.b=c;
    }

    public static void main(String[] args) {
        SynchMessage s=new SynchMessage();
        s.setii(12);
        System.out.println(s.b+"----------"+s.i);
    }
}
