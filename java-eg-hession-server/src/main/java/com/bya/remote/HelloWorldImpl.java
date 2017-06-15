package com.bya.remote;

import org.springframework.stereotype.Service;

/**
 * Created by bqct_bya on 2017/6/11.
 */
@Service("helloWorld")
public class HelloWorldImpl implements HelloWorld{

    @Override
    public void sayHello(String name) {
        System.out.println("hello "+name);
    }
}
