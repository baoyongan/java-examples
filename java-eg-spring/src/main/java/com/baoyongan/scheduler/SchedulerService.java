package com.baoyongan.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerService {


    @Scheduled(fixedDelay=5000)  // 固定延迟每五秒调用以下方法，这意味着该周期是从每个先前调用的完成时间开始测量
    public void dofixedDelaySomething() {
        System.out.println("dofixedDelaySomething...");
    }

    @Scheduled(fixedRate=5000)  // 固定速率执行。每五秒调用以下方法（在每次调用的连续开始时间之间测量）
    public void dofixedRateSomething() {
        System.out.println("dofixedRateSomething...");
    }

    @Scheduled(initialDelay=1000, fixedRate=5000)
    public void dofixedRateAndDelaySomething() {
        System.out.println("dofixedRateAndDelaySomething...");
    }

    @Scheduled(cron="*/3 * * * * *")  // cron表达式
    public void doSomething() {
        System.out.println(" cron表达式...");
    }
}
