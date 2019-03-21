package com.baoyongan.scheduler;

import com.baoyongan.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchedulerConfigTest extends BaseTest {

    public ApplicationContext context;

    @Before()
    public void initSpring(){
        context=new AnnotationConfigApplicationContext(SchedulerConfig.class);
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
