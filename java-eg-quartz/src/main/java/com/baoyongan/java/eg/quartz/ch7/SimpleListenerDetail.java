package com.baoyongan.java.eg.quartz.ch7;

import com.baoyongan.java.eg.quartz.jobs.SimpleJob;
import com.baoyongan.java.eg.quartz.listeners.SimpleTriggerListener;
import com.baoyongan.java.eg.quartz.listeners.SimpleWarnJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.EverythingMatcher.allJobs;
import static org.quartz.impl.matchers.EverythingMatcher.allTriggers;

/**
 *
 * Created by bqct_bya on 2017/5/7.
 */
public class SimpleListenerDetail {

    /*
       侦听器是您根据调度程序中发生的事件创建的对象。 您可能会猜到，TriggerListeners接收到与触发器相关的事件，JobListeners会接收与作业相关的事件。

       触发器相关的事件包括：触发器触发，触发失灵（在本文档的“触发器”部分中讨论），并触发完成（触发器关闭的作业完成）。
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
            SimpleWarnJobListener jobListener=new SimpleWarnJobListener();
            jobListener.setName("JOB监听器");

            SimpleTriggerListener triggerListener=new SimpleTriggerListener();
            triggerListener.setName("Trigger监听器");

            sched.getListenerManager().addJobListener(jobListener,allJobs());
            sched.getListenerManager().addTriggerListener(triggerListener,allTriggers());

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
            // sched.scheduleJob(trigger);
            Thread.sleep(1000000000); // 等待执行
            sched.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
