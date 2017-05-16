package com.baoyongan.java.eg.quartz.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bqct_bya on 2017/5/16.
 */
public class SimpleTriggerListener extends TriggerListenerSupport {

    private String name;
    Logger log= LoggerFactory.getLogger(SimpleTriggerListener.class);

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.error("{}，在trigger 被触发，于之关联的jobDetail将要执行。",name);
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return true; // job 将被否决而不执行
        //  return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.error("{} misfire 发生了",name);
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        log.error("{} 执行完成",name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
