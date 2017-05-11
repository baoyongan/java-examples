package com.baoyongan.java.eg.quartz.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bqct_bya on 2017/5/7.
 */

@DisallowConcurrentExecution  // is an annotation that can be added to the Job class that tells Quartz not to execute multiple instances of a given job definition (that refers to the given job class) concurrently.
public class AnnotationJob implements Job{

    Logger logger= LoggerFactory.getLogger(AnnotationJob.class);
    private String name;

    public void setName(String name) { // 可以将jobDataMap中的同名key注入
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key=jobExecutionContext.getJobDetail().getKey();
        JobDataMap map=jobExecutionContext.getJobDetail().getJobDataMap();
        String jobDetail_name= map.getString("name");
        int age=map.getInt("age");
        logger.info("JobKey={},jobDetail_name={},age={}",key,jobDetail_name,age);
        JobDataMap mergeData= jobExecutionContext.getMergedJobDataMap(); // trigger 和 jobDetail 合并过后的 同名的话，trigger 的优先级更高
        String mergename=mergeData.getString("name");
        String birth=mergeData.getString("birth");
        logger.info("JobKey={},mergename={},birth={}",key,mergename,birth);
        logger.info("Setting-name={}",this.name);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
