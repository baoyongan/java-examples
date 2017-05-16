package com.baoyongan.java.eg.quartz.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bqct_bya on 2017/5/16.
 */
public class SimpleWarnJobListener extends JobListenerSupport {

    Logger logger= LoggerFactory.getLogger(SimpleWarnJobListener.class);
    private String name;


    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        super.jobToBeExecuted(context);
        logger.error("{}将要执行",name);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        super.jobExecutionVetoed(context);
        logger.error("TriggerListener 否决了{}执行",name);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
        logger.info("{}执行完成",name);
        if(null!=jobException)
            logger.error("执行发生异常：",jobException);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
