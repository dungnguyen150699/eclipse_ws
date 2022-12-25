package com.java8.springboot.java.java8.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//https://www.tutorialspoint.com/java8/java8_streams.htm
public class StreamMethods {
	
	// thôi vào link mà đọc cho nhanh
	
	//map + distinst
	public void map_Distinst() {
		System.out.println("\nMap + Distinst :");
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		//get list of unique squares
		List<String> squaresList = numbers.stream().map( i -> i + "").distinct().collect(Collectors.toList());
		squaresList.forEach(System.out::print);
	}
	
	// limit + forEach
	public void limit_RandomReturnStream() {
		System.out.println("\nLimit + ForEach : ");
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::print);
	}
	
	public static void main(String[] args) {
		StreamMethods sm = new StreamMethods();
		sm.map_Distinst();
		sm.limit_RandomReturnStream();
	}
	
}
