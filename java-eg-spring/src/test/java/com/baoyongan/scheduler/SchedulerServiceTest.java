package com.baoyongan.scheduler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchedulerServiceTest{

    public ApplicationContext context;

    @Before()
    public void initSpring(){
        context=new ClassPathXmlApplicationContext("classpath:scheduler/scheduler.xml");
    }

    @Test
    public void schedulerTest(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeContext(){
        if(null!=context && context instanceof ClassPathXmlApplicationContext){
            ClassPathXmlApplicationContext classPathXmlApplicationContext=(ClassPathXmlApplicationContext) context;
            classPathXmlApplicationContext.destroy();
        }
    }
}
