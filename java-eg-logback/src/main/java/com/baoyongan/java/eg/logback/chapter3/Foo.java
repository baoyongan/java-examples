package com.baoyongan.java.eg.logback.chapter3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bqct_bya on 2017/3/31.
 */
public class Foo {
    Logger log=LoggerFactory.getLogger(Foo.class);
    public void doit(){
        log.debug("执行。。。。");
    }
}
