package com.baoyongan.java.eg.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportEg {
	
	public void lock(){
		 System.out.println("发起阻塞");
//		 LockSupport.park();
//		 LockSupport.park(this);
		 LockSupport.parkNanos(this, TimeUnit.SECONDS.toNanos(10));
		 System.out.println("解除阻塞");
		 doTher("主");
	}
	
	public void doTher(String tr) {
		 System.out.println("todo OTHer By "+tr);
	}
	
	public static void main(String[] args) {
		final LockSupportEg lo=new LockSupportEg();
		final Thread tread=Thread.currentThread();
		Thread sss=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lo.doTher("次");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("进行解除");
				LockSupport.unpark(tread);
			}
		});

		sss.start(); // 做其他任务，在特定的时间点唤醒原来的阻塞
		lo.lock(); // 阻塞自己（最长时间10秒）
//		LockSupport.park();
		System.out.println("ss");
	}

}
