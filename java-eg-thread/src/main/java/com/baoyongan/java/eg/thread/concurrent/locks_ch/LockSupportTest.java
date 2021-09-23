package com.baoyongan.java.eg.thread.concurrent.locks_ch;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * 用来创建锁和其他同步类的基本线程阻塞原语。
 * 此类以及每个使用它的线程与一个许可关联（从 Semaphore 类的意义上说）。
 * 如果该许可可用，并且可在进程中使用，则调用 park 将立即返回；否则可能 阻塞。如果许可尚不可用，则可以调用 unpark 使其可用。
 * （但与 Semaphore 不同的是，许可不能累积，并且最多只能有一个许可。）
 */
public class LockSupportTest {
	
	public void lock(){
		 System.out.println("发起阻塞");
		 LockSupport.park();
//		 LockSupport.park(this);
//		 LockSupport.parkNanos(this, TimeUnit.SECONDS.toSeconds(1));
		 System.out.println("解除阻塞");
		 doTher("主");
	}
	
	public void doTher(String tr) {
		 System.out.println("todo OTHer By "+tr);
	}
	
	public static void main(String[] args) {
		final LockSupportTest lo=new LockSupportTest();
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
