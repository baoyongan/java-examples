package com.baoyongan.java.eg.quartz.ch6;

import com.baoyongan.java.eg.quartz.jobs.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

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
public class SimpleCronTriggerDetail {

    /*
        CronTrigger
            描述：CronTrigger通常比SimpleTrigger更有用，如果您需要基于日历的概念而重新发布的作业启动计划，而不是SimpleTrigger的精确指定的间隔。
            属性：
            startTime: 何时生效
            endTime: 何时停止计划
        Cron Expressions
            second
            minute
            hour
            day of month
            month
            day of week
            year(option field)

            单个表达式可以表达范围或列表 eg:“MON-FRI”;"MON-WED,SAT"
            "*" 通配符
            "/" 增量值
            "?" 字符允许day of month 和 day of week 字段。用于指定“无特定值”
            "L" 字符允许day of month 和 day of week 字段。表示最后的. 重要的是不要指定列表或值的范围，因为您会得到混淆/意外的结果
                The ‘L’ character is allowed for the day-of-month and day-of-week fields. This character is short-hand for “last”, but it has different meaning in each of the two fields. For example, the value “L” in the day-of-month field means “the last day of the month” - day 31 for January, day 28 for February on non-leap years. If used in the day-of-week field by itself, it simply means “7” or “SAT”. But if used in the day-of-week field after another value, it means “the last xxx day of the month” - for example “6L” or “FRIL” both mean “the last friday of the month”. You can also specify an offset from the last day of the month, such as “L-3” which would mean the third-to-last day of the calendar month. When using the ‘L’ option, it is important not to specify lists, or ranges of values, as you’ll get confusing/unexpected results.
            "W" 字符允许day of week字段。表示给定日期最近的。
                The ‘W’ is used to specify the weekday (Monday-Friday) nearest the given day. As an example, if you were to specify “15W” as the value for the day-of-month field, the meaning is: “the nearest weekday to the 15th of the month”.
            "#" '＃'用于指定本月的“第n个”XXX工作日。例如，星期几字段中的“6＃3”或“FRI＃3”的值表示“本月的第三个星期五”
                The "#" is used to specify “the nth” XXX weekday of the month. For example, the value of “6#3” or “FRI#3” in the day-of-week field means “the third Friday of the month”
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
