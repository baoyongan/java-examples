package com.baoyongan.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync // 可以@Async在方法上提供注释，以便异步调用该方法。换句话说，调用程序在调用时立即返回，而方法的实际执行发生在已提交给Spring的任务中TaskExecutor。
@EnableScheduling // 可以将@Scheduled注释添加到方法以及触发器元数据。
public class SchedulerConfig {

    @Bean
    public SchedulerService schedulerService(){
        return new SchedulerService();
    }
}
