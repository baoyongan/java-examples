package com.baoyongan.java.eg.thread.threadpool;

import java.util.concurrent.*;

public class ExecutorEg {
	public static void main(String[] args) { 
		
		// 固定一个线程池的执行就是这样
		Executors.newSingleThreadExecutor(); 
		// 等同于以下这种实现 core=max=1 queue=无界队列 keepAliveTime=0s
		new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	
		// 固定线程池服务
		int threadSize=3;
		Executors.newFixedThreadPool(threadSize);
		// 等同于 core=max=threadSize queue=无界队列 keepAliveTime=0s
		new ThreadPoolExecutor(threadSize, threadSize,0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		
		// 无界线程池，可以进行自动线程回收
		Executors.newCachedThreadPool();
		// 等同于core=0 max=int.max keepAliveTime=60s 
		new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		
		//

		ScheduledExecutorService schedule=Executors.newScheduledThreadPool(3);
//		schedule.

	}
		
}
