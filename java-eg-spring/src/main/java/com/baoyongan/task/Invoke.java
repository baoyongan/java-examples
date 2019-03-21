package com.baoyongan.task;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Invoke {

    @Resource
    private AsyncExample asyncExample;

    public void invokeAysnc(){
        System.out.println("开始 invoke");
        asyncExample.doSomething();
        System.out.println("结束 invoke");
    }
}
