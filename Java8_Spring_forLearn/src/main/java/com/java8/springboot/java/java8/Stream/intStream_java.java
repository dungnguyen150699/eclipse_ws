package com.java8.springboot.java.java8.Stream;


import java.util.Optional;
import java.util.stream.IntStream;


public class intStream_java {
	public static void main(String...strings ) {
		IntStream.range(1, 5);  	//1,2,3,4

		IntStream.rangeClosed(1, 5);  	//1,2,3,4,5
		IntStream x = IntStream.iterate(0, i -> i + 2).limit(10);	

		//0,2,4,6,8,10,12,14,16,18
		System.out.println(x.average().orElse(0));
	}
}
