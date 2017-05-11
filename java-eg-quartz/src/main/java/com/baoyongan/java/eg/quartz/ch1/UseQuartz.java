package com.baoyongan.java.eg.quartz.ch1;

import com.baoyongan.java.eg.quartz.jobs.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 试用
 * Created by bqct_bya on 2017/5/7.
 */
public class UseQuartz {

    public static void main(String[] args) {
        try {

            /*
            在使用调度程序之前，需要实例化（谁猜到了？）。为此，您可以使用SchedulerFactory。Quartz的一些用户可能会在JNDI存储中保留工厂的实例，其他用户可能会发现直接实例化和使用工厂实例（例如下面的示例）很简单（或更简单）。
            一旦调度程序被实例化，它可以被启动，置于待机模式并关闭。请注意，一旦调度程序关闭，它不能重新启动而不重新实例化。触发器不启动（作业不执行），直到调度程序已启动，也不处于暂停状态。
             */
            // 创建工厂
            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            // 获取实例
            Scheduler sched = schedFact.getScheduler();
            // 启动
            sched.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);

            Thread.sleep(1000000000);
            sched.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
