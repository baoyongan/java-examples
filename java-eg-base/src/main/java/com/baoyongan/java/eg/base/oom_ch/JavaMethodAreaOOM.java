package com.baoyongan.java.eg.base.oom_ch;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class JavaMethodAreaOOM {
	
	/**
	 * VM args:-XX:PermSize=10M -XX:MaxPermSize=10M
	 * @param args
	 */
	public static void main(String[] args) {
		int i=0;
		while (true) {
			System.out.println(i++);
			Enhancer en=new Enhancer();
			en.setSuperclass(HeapOOM.OOMObject.class);
			en.setUseCache(false);
			en.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
					return arg3.invokeSuper(arg0, arg2);
				}
			});
			en.create();
			
		}
	}
}
