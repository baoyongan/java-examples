package com.baoyongan.java.eg.quartz.ch3;

import com.baoyongan.java.eg.quartz.jobs.AnnotationJob;
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
public class AnnoSche {

    public static void main(String[] args) {
        try {

            /*
           现在，关于作业的状态数据（又名JobDataMap）和并发的一些附加说明。有几个注释可以添加到Job类中，这些注释会影响Quartz关于这些方面的行为。

            @DisallowConcurrentExecution是一个注释，可以添加到Job类中，该类可以告诉Quartz不要同时执行给定作业定义（引用给定作业类）的多个实例。
            注意那里的措辞，因为它被非常仔细地选择。在上一节的示例中，如果“SalesReportJob”具有此注释，则只能在给定时间执行一个“SalesReportForJoe”实例，但它可以与“SalesReportForMike”实例同时执行。约束基于实例定义（JobDetail），而不是作业类的实例。然而，决定（在Quartz的设计期间）将注释载入类本身，因为它通常会对类的编码方式产生影响。

            @PersistJobDataAfterExecution是一个注释，可以添加到Job类中，该类可以告诉Quartz在execute（）方法成功完成后（而不会抛出异常）更新JobDetail的JobDataMap的存储副本，以便下次执行相同的作业JobDetail）接收更新的值而不是原始存储的值。像@DisallowConcurrentExecution注释一样，这适用于作业定义实例，而不是作业类实例，尽管它决定让作业类携带属性，因为它通常会对类的编码有所不同（例如“状态” '需要被execute方法中的代码明确地“理解”）。

            如果您使用@PersistJobDataAfterExecution注释，则应强烈考虑使用@DisallowConcurrentExecution注释，以避免同时执行同一作业（JobDetail）的两个实例时可能存在什么数据的可能混淆（竞争条件）。
             */
            // 创建工厂
            SchedulerFactory schedFact = new StdSchedulerFactory();
            // 获取实例
            Scheduler sched = schedFact.getScheduler();
            // 启动
            sched.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(AnnotationJob.class)    // schedule 调用job的时候 是新new一个job对象。所以job 无法保存状态或者属性值，可以通过jobDataMap
                    .withIdentity("myJob_0", "group1")
                    .usingJobData("name","鲍安")
                    .usingJobData("age",21)
                    .build();
            JobDetail job1 = newJob(AnnotationJob.class)    // schedule 调用job的时候 是新new一个job对象。所以job 无法保存状态或者属性值，可以通过jobDataMap
                    .withIdentity("myJob_1", "group2")
                    .usingJobData("name","鲍永")
                    .usingJobData("age",20)
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
            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger1 = newTrigger()
                    .withIdentity("myTrigger_1", "group2")
                    .usingJobData("name","bya-trigger")   // 同名的话，trigger 的优先级更高
                    .usingJobData("birth","0908")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
            sched.scheduleJob(job1, trigger1);
            Thread.sleep(1000000000); // 等待执行
            sched.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
