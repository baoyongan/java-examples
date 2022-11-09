package com.bya.java.eg.learn.rxjava;

import io.reactivex.rxjava3.core.Flowable;

public class HelloWorld {

    public static void main(String[] args) {
        Flowable.just("Helloword").subscribe(System.out::println);
    }
}
