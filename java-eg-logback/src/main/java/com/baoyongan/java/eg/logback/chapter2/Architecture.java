package com.baoyongan.java.eg.logback.chapter2;

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
     */
    public static void main(String[] args) {

    }

}
