package com.baoyongan.java.eg.thread.concurrent.executor.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorForKeepAliveTime {
	public static void main(String[] args) {
		
		/**
		 * 什么时候会新建max-core那些线程，就是当core用完，queue用完，新来的请求就会用max-corede ,再来请求就是拒绝的策略了。
		 * keepAliveTime 是针对当线程数>core线程数的时候，queue又没有满的时候，新建的线程也就是（max-core）的那些线程在空闲的时候最长存活时间
		 * 
		 */
		// 下面这个例子是core=1 max=3 keepAliveTime=3s queueSize=1
		// 我们可以看到在-----3-----结束后等了3s，线程池的线程数变成了1
		ThreadPoolExecutor executors=new ThreadPoolExecutor(1, 2,3L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1));
		
		executors.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("-----1-----over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// 
		executors.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("-----2-----over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		executors.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("-----3-----over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		/*executors.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("-----4-----over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});*/
		
		System.out.println("=======================================================");
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(executors);
		}
	}
}
