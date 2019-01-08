package com.baoyongan.property;

import com.baoyongan.bean.FooValue;
import com.baoyongan.task.Invoke;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertiesTest {

    public ApplicationContext context;

    @Before()
    public void initSpring(){
        context=new ClassPathXmlApplicationContext("classpath:property.xml");
    }

    @Test
    public void valueTest()  {
        FooValue i=context.getBean(FooValue.class);
        System.out.println(i);
    }

    @After
    public void closeContext(){
        if(null!=context && context instanceof ClassPathXmlApplicationContext){
            ClassPathXmlApplicationContext classPathXmlApplicationContext=(ClassPathXmlApplicationContext) context;
            classPathXmlApplicationContext.destroy();
        }
    }
}
