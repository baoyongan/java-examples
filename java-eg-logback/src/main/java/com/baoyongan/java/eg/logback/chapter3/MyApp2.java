package com.baoyongan.java.eg.logback.chapter3;

import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Date;

/**
 * 实验堆栈打印包信息不起作用
 * Created by bqct_bya on 2017/3/31.
 */
public class MyApp2 {
    static Logger log = LoggerFactory.getLogger(MyApp2.class);

    public static void main(String[] args) throws SQLException {
        /**
         * 在resource下满添加logback.xml
         */
        log.info("开始运行。。。。");
        toInvoke();
        log.info("结束运行");
    }

    private static void toInvoke() throws SQLException {
        BasicDataSource db=new BasicDataSource();
        db.setDriverClassName("com.mysql.jdbc.Driver");
        db.setUrl("jdbc:mysql://192.16.1.239:13306/crm");
        db.setUsername("crmvip");
        db.setPassword("crmvip");
        db.getConnection();
    }
}
