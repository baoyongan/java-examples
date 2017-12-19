package com.baoyongan.java.eg.thread.queue;

public class NativeSynchronousQueue<E> {
	boolean putting=false;
	E item=null;
	
	public synchronized E take() throws InterruptedException{
		while (item==null) {
			System.out.println("-------item为null--take 进入等待");
			wait();// 当前线程等待
		}
		E e=item;
		item=null;
		notifyAll();// 唤醒所有监听此对象的线程 
		System.out.println("--------已经取得item-take 唤醒其他线程");
		return e;
	}
	
	public synchronized void put(E e) throws InterruptedException{
		if(null==e)
			return;
		while (putting) {
			System.out.println("---------有写入---put进入等待");
			wait();// 当正在put的时候，当前线程等待
		}
		putting=true; // 设置自己正在put
		item=e;
		notifyAll(); // 唤醒其他线程取走对象
		System.out.println("---------put item完成---唤醒其他线程");
		while (item!=null) {
			System.out.println("---------item未被取走---进入等待");
			wait(); // 等待对象被取走
		}
		putting =false; // 已经被取走，put完成 
		notifyAll();// 唤醒其他线程可以继续放入  
		System.out.println("---------put操作完成---唤醒其他put");
	}
	
	public static void main(String[] args) throws InterruptedException {
		// 
		NativeSynchronousQueue<String> nsq=new NativeSynchronousQueue<String>();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("取到==================="+nsq.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("开始put===================resss");
					nsq.put("resss");
					System.out.println("put完成===================resss");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
