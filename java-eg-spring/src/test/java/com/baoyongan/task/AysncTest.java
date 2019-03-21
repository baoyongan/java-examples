package com.baoyongan.task;

import com.baoyongan.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AysncTest{

    public ApplicationContext context;

    @Before()
    public void initSpring(){
        context=new ClassPathXmlApplicationContext("classpath:scheduler/scheduler.xml");
    }

    @Test
    public void AysncTest() throws InterruptedException {
        Invoke i=context.getBean(Invoke.class);
        i.invokeAysnc();

        Thread.sleep(6000);
    }

    @After
    public void closeContext(){
        if(null!=context && context instanceof ClassPathXmlApplicationContext){
            ClassPathXmlApplicationContext classPathXmlApplicationContext=(ClassPathXmlApplicationContext) context;
            classPathXmlApplicationContext.destroy();
        }
    }
}
