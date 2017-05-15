package com.baoyongan.java.eg.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bqct_bya on 2017/5/7.
 */
@DisallowConcurrentExecution
public class SimpleJob implements Job{

    Logger logger= LoggerFactory.getLogger(SimpleJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("start do job......");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("do job end......");
    }
}
