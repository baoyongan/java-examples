package com.bya.test;

import base.BaseTest;
import com.bya.remote.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by bqct_bya on 2017/6/11.
 */
public class HelloWordTest extends BaseTest {

    @Autowired
    @Qualifier("helloService")
    private HessianProxyFactoryBean helloService;

    public void helloTest(){
       HelloWorld helloWorld= (HelloWorld) helloService.getObject();
       helloWorld.sayHello("baozi");
    }


}
