package com.baoyongan.java.eg.quartz.listeners;

import org.quartz.JobDetail;
import org.quartz.listeners.SchedulerListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bqct_bya on 2017/5/16.
 */
public class SimpleSchedulerListener extends SchedulerListenerSupport {

    Logger log= LoggerFactory.getLogger(SimpleSchedulerListener.class);

    @Override
    public void jobAdded(JobDetail jobDetail) {
        log.info("新的job被添加了--{}",jobDetail.toString());
    }

    @Override
    public void schedulerStarted() {
        log.info("scheduler启动了");
    }

    @Override
    public void schedulerShutdown() {
        log.info("scheduler关闭了");
    }

}
