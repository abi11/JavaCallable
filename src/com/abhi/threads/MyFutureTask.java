package com.abhi.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyFutureTask {

	public static void main(String[] args) {
		
		MyFutureCallable fCallable1= new MyFutureCallable(1000);
		MyFutureCallable fCallable2= new MyFutureCallable(2000);
		
		FutureTask<String> fTask1= new FutureTask<String>(fCallable1);
		FutureTask<String> fTask2= new FutureTask<String>(fCallable2);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(fTask1);
		executor.execute(fTask2);
		
		while(true) {
			try {
				if(fTask1.isDone() && fTask2.isDone()) {
					System.out.println("Completed");
					executor.shutdown();
					return;
				}
				if(!fTask1.isDone() ) {
					System.out.println("fTask output="+ fTask1.get());
				}
				System.out.println("wating for fTask2 to complete");
				String s = fTask2.get(200L, TimeUnit.MILLISECONDS);
				if(null!= s) {
					System.out.println("fTask output=" +s);
				}
				
			}catch(InterruptedException |ExecutionException e) {
				e.printStackTrace();
			}catch(TimeoutException t) {
		}
		}

	}

}
