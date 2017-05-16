package com.baoyongan.java.eg.quartz.ch8;

import com.baoyongan.java.eg.quartz.jobs.SimpleJob;
import com.baoyongan.java.eg.quartz.listeners.SimpleSchedulerListener;
import com.baoyongan.java.eg.quartz.listeners.SimpleTriggerListener;
import com.baoyongan.java.eg.quartz.listeners.SimpleWarnJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.EverythingMatcher.allJobs;
import static org.quartz.impl.matchers.EverythingMatcher.allTriggers;

/**
 *
 * Created by bqct_bya on 2017/5/7.
 */
public class SimpleSchehulerListenerDetail {

    /*
     SchedulerListeners非常像TriggerListeners和JobListeners，除了它们在Scheduler本身中接收到事件的通知 - 不一定是与特定触发器或作业相关的事件。

        与调度相关的事件包括：添加作业/触发器，删除作业/触发器，调度程序中的严重错误，关闭调度程序的通知等。
     */


    public static void main(String[] args) {
        try {
            // 创建工厂
            SchedulerFactory schedFact = new StdSchedulerFactory();
            // 获取实例
            Scheduler sched = schedFact.getScheduler();

            // 启动
            sched.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(SimpleJob.class)
                    .withIdentity("myJob")
                    .build();

            // 1、在特定的时间运行，重复执行3次，一次间隔5秒  可以测试job注解@DisallowConcurrentExecution
           Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    //.withSchedule(simpleSchedule().withIntervalInSeconds(5).withRepeatCount(3)) // 重复三次加上第一次就是一共4 次
                    .build();

           // listener
            SimpleSchedulerListener schedulerListener;
            schedulerListener = new SimpleSchedulerListener();

            sched.getListenerManager().addSchedulerListener(schedulerListener);

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
            // sched.scheduleJob(trigger);
            Thread.sleep(20000); // 等待执行
            sched.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
