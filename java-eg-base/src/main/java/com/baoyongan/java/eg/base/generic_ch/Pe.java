package com.baoyongan.java.eg.base.generic_ch;

public interface Pe {

    default void run(){
        System.out.println("hello");
    }

    static void gan(){
        System.out.println("Gan");
    }
}
