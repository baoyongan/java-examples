package com.baoyongan.java.eg.logback.chapter1;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在没有找到logback配置文件时，会默认添加ConsoleAppender To the root logger.
 * * Created by bqct_bya on 2017/3/30.
 */
public class HelloWorld {
    public static void main(String[] args) {
       Logger log= LoggerFactory.getLogger("com.baoyongan.java.eg.logback.chapter1.HelloWorld");
       log.debug("Hello World!");

        /*logback 可以使用状态系统报告它的内部情形。
          logback 生命周期中重要的事件发生时，会通过“StatusManager”存取
        */
        // 下面 我们来指导logback 打印他的内部状态
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);

    }
}
