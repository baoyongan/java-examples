package com.baoyongan.java.eg.quartz.ch3;

import com.baoyongan.java.eg.quartz.jobs.HelloJob;
import com.baoyongan.java.eg.quartz.jobs.UseDataJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 试用
 * Created by bqct_bya on 2017/5/7.
 */
public class JobAndJobDetail {

    public static void main(String[] args) {
        try {

            /*
            请注意，我们给调度程序一个JobDetail实例，并且通过在构建JobDetail时简单地提供作业的类，它知道要执行的作业的类型。
            调度程序执行作业的每一个（和每个）时间，它在调用其execute（..）方法之前创建一个新的类的实例。
            执行完成后，对作业类实例的引用将被删除，然后将该实例进行垃圾回收。
            这种行为的一个后果是，作业必须具有无参数构造函数（使用默认JobFactory实现时）。
            另一个结论是，在作业类上定义状态数据字段是没有意义的 - 因为它们的值不会在作业执行之间保留。
            您可能现在想要问“如何为Job实例提供属性/配置”？“以及”如何跟踪执行之间的工作状态？“这些问题的答案是一样的：关键是JobDataMap ，它是JobDetail对象的一部分。
             */
            // 创建工厂
            SchedulerFactory schedFact = new StdSchedulerFactory();
            // 获取实例
            Scheduler sched = schedFact.getScheduler();
            // 启动
            sched.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(UseDataJob.class)    // schedule 调用job的时候 是新new一个job对象。所以job 无法保存状态或者属性值，可以通过jobDataMap
                    .withIdentity("myJob", "group1")
                    .usingJobData("name","鲍永安")
                    .usingJobData("age",26)
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .usingJobData("name","bya-trigger")   // 同名的话，trigger 的优先级更高
                    .usingJobData("birth","0908")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);

            Thread.sleep(1000000000); // 等待执行
            sched.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
