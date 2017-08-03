package com.baoyongan.java.eg.base.shutdown;

import sun.misc.Signal;

/**
 * APP
 */
public class App 
{
    public static final String SIGNAL_NAME="USR2"; // linux 中用户自定义的信号量。 kill -12 触发
    public static void main( String[] args )
    {
        // TODO 应用逻辑

        // 添加linux 信号量的监听，处理应用关闭的操作
        ShutDown shutdown=new ShutDown();
    	Signal.handle(new Signal(SIGNAL_NAME), shutdown);
    }
}
