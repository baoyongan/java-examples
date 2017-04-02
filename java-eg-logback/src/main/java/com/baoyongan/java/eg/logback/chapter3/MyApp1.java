package com.baoyongan.java.eg.logback.chapter3;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bqct_bya on 2017/3/31.
 */
public class MyApp1 {
    static Logger log = LoggerFactory.getLogger(MyApp1.class);

    public static void main(String[] args) {

        /**
         * 在resource下满添加logback.xml
         */
        log.info("开始运行。。。。");
        Foo foo = new Foo();
        foo.doit();
        log.info("结束运行");

        /* 查看日志内部状态 可以在logback.xml 的<configuration debug="true"> 来打印logback的内部状态。不必在代码中显示调用；
        *  但是在找不到配置文件的情况下，是不会打印状态信息的。代码调用的是可以的。 */
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);

    }
}
