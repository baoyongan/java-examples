package com.baoyongan.java.eg.logback.chapter3;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 修改logback 的默认配置JoranConfigurator ，来完成日志配置
 * Created by bqct_bya on 2017/3/31.
 */
public class MyApp3 {
    final static Logger logger = LoggerFactory.getLogger(MyApp3.class);

    public static void main(String[] args) {
        // assume SLF4J is bound to logback in the current environment
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g. default
            // configuration. For multi-step configuration, omit calling context.reset().
            context.reset();
            configurator.doConfigure("D:\\Intellij_project\\java-examples\\java-eg-logback\\src\\main\\resources\\logback0.xml");
        } catch (Exception je) {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        logger.info("Entering application.");

        Foo foo = new Foo();
        foo.doit();
        logger.info("Exiting application.");
    }
}
