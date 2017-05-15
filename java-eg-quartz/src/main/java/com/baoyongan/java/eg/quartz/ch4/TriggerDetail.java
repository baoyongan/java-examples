package com.baoyongan.java.eg.quartz.ch4;

import com.baoyongan.java.eg.quartz.jobs.SimpleJob;
import com.baoyongan.java.eg.quartz.jobs.UseDataJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 *
 * Created by bqct_bya on 2017/5/7.
 */
public class TriggerDetail {

    /*
        trigger 属性：
            triggerKey: trigger id 定义
            jobKey: trigger 要执行的job id
            startTime: trigger计划首次执行的时间，必须在该时间之后
            endTime: trigger计划失效的时间，在该时间之后将不再执行
            priority: trigger优先级,当同一时间点触发的计划，而工作线程又有有限的，将给优先级高的优先执行。
            misfire instructions: 在执行时间点错过执行的计划策略。eg:服务关闭；没有足够的工作线程执行等。默认的策略是“smart policy”
            Calendars: 可以在触发器的触发时间表中排除一段时间。可以在触发器定义时存储在调度程序中时，与触发器相关联
     */


    public static void main(String[] args) {
        try {

            HolidayCalendar cal= new HolidayCalendar();
            cal.addExcludedDate(new Date()); // 今天不执行

            // 创建工厂
            SchedulerFactory schedFact = new StdSchedulerFactory();
            // 获取实例
            Scheduler sched = schedFact.getScheduler();
            sched.addCalendar("myHoliday",cal,false,false);
            // 启动
            sched.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(SimpleJob.class)
                    .withIdentity("myJob")
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    .modifiedByCalendar("myHoliday")
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
