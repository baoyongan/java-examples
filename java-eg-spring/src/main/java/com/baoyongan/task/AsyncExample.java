package com.baoyongan.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureAdapter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class AsyncExample {

    @Async
    void doSomething() {
        System.out.println("异步执行开始。。。。");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步执行结束。。。。");
    }

}
