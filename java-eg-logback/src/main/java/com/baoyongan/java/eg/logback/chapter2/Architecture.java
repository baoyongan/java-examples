package com.baoyongan.java.eg.logback.chapter2;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback 架构
 * logback-core Appender(输出源) and Layout(布局) is part of this
 * logback-classic Logger(日志) is part of this
 * Created by bqct_bya on 2017/3/30.
 */
public class Architecture {
    /**
     * 首要的也是最重要的优势对于任何一个日志API与"system.out.println"相比，
     * 在于它可以禁止某些日志语句同时允许其他打印不受影响。
     * 这个能力假设日志空间/间隔，那么所有的日志语句空间按照开发人员选择的分类。
     * 在logback-classic,分类是其固有的一部分。每个日志都属于"LoggerContext"，负责生产日志，同时像个树一样分层排列他们。
     *
     * 等级分层和请求级别之间的关系
     * TRACE < DEBUG < INFO <  WARN < ERROR.
     */



    public static void main(String[] args) {
        // The root logger resides at the top of the logger hierarchy. It is exceptional in that it is part of every hierarchy at its inception. Like every logger, it can be retrieved by its name, as follows:
        // logger 日志的顶层，所有的日志都继承自它
        Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        // get a logger instance named "com.foo". Let us further assume that the
        // logger is of type  ch.qos.logback.classic.Logger so that we can
        // set its level
        // 获取一个logger 设置其有效级别是INFO
        ch.qos.logback.classic.Logger logger= (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        logger.setLevel(Level.DEBUG);

        // 下面的barlogger 是上面的logger 的子log
        Logger barLogger=LoggerFactory.getLogger("com.foo.Bar");
        logger.warn("这是父logger的警告请求日志");
        logger.debug("这是父logger的调试级别请求日志");

        // This request is enabled, because WARN >= INFO
        logger.warn("Low fuel level.");

        // This request is disabled, because DEBUG < INFO.
        logger.debug("Starting search for nearest gas station.");

        // The logger instance barlogger, named "com.foo.Bar",
        // will inherit its level from the logger named
        // "com.foo" Thus, the following request is enabled
        // because INFO >= INFO.
        barLogger.info("Located nearest gas station.");

        // This request is disabled, because DEBUG < INFO.
        barLogger.debug("Exiting gas station search");

        // 同名的Logger 是相同的日志对象
        Logger x = LoggerFactory.getLogger("wombat");
        Logger y = LoggerFactory.getLogger("wombat");
        System.out.println("logger-x==logger-y?"+(x==y));

        // 日志输出源Appender的配置
        /**
         * 可以添加多个Apppender，对一个logger.
         * 如果配置多个logger的appender，appender会拥有和logger 一样的继承关系并打印。
         * The addAppender method adds an appender to a given logger. Each enabled logging request for a given logger will be forwarded to all the appenders in that logger as well as the appenders higher in the hierarchy. In other words, appenders are inherited additively from the logger hierarchy. For example, if a console appender is added to the root logger, then all enabled logging requests will at least print on the console. If in addition a file appender is added to a logger, say L, then enabled logging requests for L and L's children will print on a file and on the console. It is possible to override this default behavior so that appender accumulation is no longer additive by setting the additivity flag of a logger to false.
         * The rules governing appender additivity are summarized below.
         * Appender Additivity
         * The output of a log statement of logger L will go to all the appenders in L and its ancestors. This is the meaning of the term "appender additivity".
         * However, if an ancestor of logger L, say P, has the additivity flag set to false, then L's output will be directed to all the appenders in L and its ancestors up to and including P but not the appenders in any of the ancestors of P.
         * Loggers have their additivity flag set to true by default.
         */

         // 参数化日志语句
        // 一个参数
        Object entry = new Object();
        logger.debug("The entry is {}.", entry);
        // 两个
        Object oldEntry = new Object();
        logger.debug("The new entry is {}. It replaces {}.", entry, oldEntry);
        // >2
        Object[] paramArray = {"bao", "yong", "an"};
        logger.debug("Value {} was inserted between {} and {}.", paramArray);



    }

}
