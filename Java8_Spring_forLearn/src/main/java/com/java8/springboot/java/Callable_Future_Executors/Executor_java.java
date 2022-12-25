package com.java8.springboot.java.Callable_Future_Executors;


import java.util.concurrent.*;

public class Executor_java {
	
	
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Calculator c1 = new Calculator(1, 2);
    Calculator c2 = new Calculator(1, 3);
    Calculator c3 = new Calculator(2, 3);
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Future<Integer> f1 = executor.submit(c1);
    Future<Integer> f2 = executor.submit(c2);
    Future<Integer> f3 = executor.submit(c3);
    System.out.println("Done");
    
    executor.shutdown();
  }
}