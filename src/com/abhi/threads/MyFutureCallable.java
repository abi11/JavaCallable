package com.abhi.threads;

import java.util.concurrent.Callable;

public class MyFutureCallable implements Callable<String>{
	
	private long waitTime;
	
	public MyFutureCallable(int timeinMillSeonds) {
		this.waitTime=timeinMillSeonds;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(waitTime);
		return Thread.currentThread().getName();
	}

}
