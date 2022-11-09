package com.baoyongan.java.eg.base.io_and_nio_ch.lineread;

import java.io.File;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 一行一行读取文件中的内容
 */
public abstract class LineReadIterable implements Iterator<String> {

    /**
     * 解析所有的行
     * @param parser
     */
    public void ParseLine(Consumer<String> parser){
//        preHandle();
        while (hasNext()){
            parser.accept(next());
        }
//        postHandle();
    }

//    protected abstract void postHandle();

//    protected abstract void preHandle();
}
