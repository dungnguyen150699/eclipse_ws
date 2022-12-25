package com.java8.springboot.java.java8.lamda;

import java.util.function.Supplier;

public class LamdaBasic {
	
	public void supplierSupportLamda() {
		Supplier<String> s = () ->  "Hello Lambda."; // Supplier hỗ trợ lamda
		System.out.println(s.get());
	}
	
	// Thường úng dụng vào Stream thế thôi
	
	public static void main(String[] args) {
		LamdaBasic lb = new LamdaBasic();
		lb.supplierSupportLamda();
	}
}
