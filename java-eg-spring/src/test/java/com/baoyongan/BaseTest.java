package com.baoyongan;

import com.baoyongan.bean.Foo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {

    public ApplicationContext context;

    @Before()
    public void initSpring(){
        context=new ClassPathXmlApplicationContext("classpath:*.xml");
    }

    @Test
    public void FooTest(){
         Foo foo= context.getBean(Foo.class);
         foo.handleLocation();
    }

    @After
    public void closeContext(){
        if(null!=context && context instanceof ClassPathXmlApplicationContext){
            ClassPathXmlApplicationContext classPathXmlApplicationContext=(ClassPathXmlApplicationContext) context;
            classPathXmlApplicationContext.destroy();
        }
    }
}
