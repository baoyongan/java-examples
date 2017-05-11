package com.baoyongan.java.eg.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bqct_bya on 2017/5/7.
 */
public class HelloJob implements Job{

    Logger logger= LoggerFactory.getLogger(HelloJob.class);
    private  AtomicLong count=new AtomicLong();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long i=count.incrementAndGet(); // 启动不是一个实例对象，所以没用
        logger.info("this is my hello-job-index 【{}】start",i);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("this is my hello-job-index 【{}】 end",i);
    }
}
