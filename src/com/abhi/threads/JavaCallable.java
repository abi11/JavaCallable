package com.abhi.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class JavaCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return Thread.currentThread().getName();
	}
	public static void main(String[] args) {
		//Get ExecutorService & thread pool size is 10
		ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<Future<String>>();
        Callable<String> callable= new JavaCallable();
        
//        IntStream.iterate(0, i -> i + 100)
//        .forEach(i->)
        
        for(int i=0;i<=100;i++) {
        	
        	Future<String> future = executor.submit(callable);
        	list.add(future);
        	
        }
        
        for(Future<String>fut : list) {
        	try {
        		System.out.println("fut.isCancelled() :" + fut.isCancelled() +" ,fut.isDone():" + fut.isDone());
        		System.out.println(new Date() + "::"+ fut.get());
        	}catch(InterruptedException | ExecutionException e) {
        		e.printStackTrace();
        	}
        }
        
        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());
        try {
        System.out.println("executor.awaitTermination(100, TimeUnit.MILLISECONDS) :"+ executor.awaitTermination(100, TimeUnit.MILLISECONDS));
        }catch (InterruptedException e) {
        	e.printStackTrace();
        }
        System.out.println(" executor.shutdownNow():" +  executor.shutdownNow());
	}
}
