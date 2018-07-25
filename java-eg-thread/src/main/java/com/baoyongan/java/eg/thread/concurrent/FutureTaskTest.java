package com.baoyongan.java.eg.thread.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 获取启动的线程的执行结果
 * @author bao
 *
 */
public class FutureTaskTest {
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	
		 
		Callable<Integer> call=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				Thread.sleep(5000);
				Integer result=new Random().nextInt(100);
				System.out.println("result :"+result);
				return result;
			}
		};
		
		FutureTask<Integer> future= new FutureTask<Integer>(call);
		new Thread(future).start();
		//Thread.sleep(2000);
		System.out.println(future.isDone());
		//System.out.println(future.cancel(true)); // 没启动则不启动，启动了则立即停止。
//		System.out.println(future.cancel(false)); // 启动了则等待完成
		System.out.println(future.isCancelled());
		System.out.println(future.isDone());
		System.out.println(future.get());
		System.out.println("future.get() is done"); 
		
		
		
		
	}
}
