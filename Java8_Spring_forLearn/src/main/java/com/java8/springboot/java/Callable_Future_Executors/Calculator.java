package com.java8.springboot.java.Callable_Future_Executors;

import java.util.concurrent.Callable;

// https://stackjava.com/java/code-vi-du-callable-future-executors-trong-java.html

public class Calculator implements Callable<Integer> {
  private int a;
  private int b;
  public Calculator(int a, int b) {
    this.a = a;
    this.b = b;
  }
  
  public int sum() {
    int sum = this.a + this.b;
    System.out.println("result: " + a + " + " + b + " = " + sum);
    return sum;
  }
  
  public static int add(int a, int b) {
	    int sum = a + b;
	    System.out.println("result: " + a + " + " + b + " = " + sum);
	    return sum;
  }
  
  @Override
  public Integer call() throws Exception {
    return this.sum();
  }
}