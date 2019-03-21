package com.baoyongan.task;

import com.baoyongan.BaseTest;
import org.junit.Test;

public class TaskExecutorExampleTest extends BaseTest {

    @Test
    public void taskExecutorTest(){
        TaskExecutorExample taskExecutorExample= (TaskExecutorExample) context.getBean("taskExecutorExample");
        taskExecutorExample.printMessages();
    }
}
