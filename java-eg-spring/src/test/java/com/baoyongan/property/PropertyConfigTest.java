package com.baoyongan.property;

import com.baoyongan.BaseTest;
import com.baoyongan.PropertiesConfig;
import com.baoyongan.scheduler.SchedulerConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyConfigTest extends BaseTest {

    public ApplicationContext context;

    @Before()
    public void initSpring(){
        context=new AnnotationConfigApplicationContext(PropertiesConfig.class);
    }

    @Test
    public void propertiesTest(){
        PropertiesConfig config=  context.getBean(PropertiesConfig.class);
        System.out.println(config);
    }

    @After
    public void closeContext(){
        if(null!=context && context instanceof ClassPathXmlApplicationContext){
            ClassPathXmlApplicationContext classPathXmlApplicationContext=(ClassPathXmlApplicationContext) context;
            classPathXmlApplicationContext.destroy();
        }
    }
}
