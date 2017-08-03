package com.baoyongan.java.eg.base.shutdown;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class ShutDown implements SignalHandler {

	@Override
	public void handle(Signal arg0) {

		if (App.SIGNAL_NAME.equals(arg0.getName())) {
			// TODO 释放系统资源destory(),close()
			System.out.print("...... closed!");
			System.out.print("#######################system.exit-0#########################");
			System.exit(0);
		}
	}
}
