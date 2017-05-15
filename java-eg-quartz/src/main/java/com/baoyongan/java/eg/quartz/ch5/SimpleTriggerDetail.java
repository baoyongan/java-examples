package com.baoyongan.java.eg.quartz.ch5;

import com.baoyongan.java.eg.quartz.jobs.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 *
 * Created by bqct_bya on 2017/5/7.
 */
public class SimpleTriggerDetail {

    /*
        SimpleTrigger
            描述： 类似于 ScheduledExecutorService
                   如果您需要在特定时刻或特定时刻执行一次作业，然后按特定间隔重复。
            属性：
            repeatCount: 重复次数
            repeatInterval: 重复间隔
            endTime:（ 如果指定）将覆盖重复计数属性。如果您希望创建一个触发器，例如每10秒触发一次直到给定的时间点，而不必计算在开始时间和结束时间之间重复的次数
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

            String timeStr="2017-05-15 19:12:06";
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time=sdf.parse(timeStr);
            // 1、在特定的时间运行，没有重复运行
            /*Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    .startAt(time) // 不指定开始时间,默认就是“now”
                    .build();*/

            // 2、在特定的时间运行，重复执行3次，一次间隔5秒  可以测试job注解@DisallowConcurrentExecution
           /* Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    // .startAt(time) // 不指定开始时间,默认就是“now”
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .withRepeatCount(3)) // 重复三次加上第一次就是一共4 次
                    .build();*/

            // 3、在10秒之后执行一次
           /* Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    .startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND)) // 使用DateBuilder构建时间
                    .build();*/

            // 4、立即执行，然后5秒执行一次，直到某个时间
           /*Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .endAt(DateBuilder.dateOf(19,38,00))
                    .build();*/

            // 5、在整分钟的时候执行，然后每一分钟执行一次
          /* Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    // .forJob("myJob") // 不指定job,需要在调度器的地方一块传参
                    .startAt(DateBuilder.evenMinuteDate(null))
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(1)
                            .repeatForever())
                    .build();*/

            // 6、指定misfire情况下的策略
           Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .withRepeatCount(3)
                            .withMisfireHandlingInstructionIgnoreMisfires())// 策略忽略掉错过执行的
                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
            // sched.scheduleJob(trigger);
            Thread.sleep(1000000000); // 等待执行
            sched.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
