package com.java8.springboot.java.java8.SuppliervsComsumer;

import java.util.function.Supplier;

// Đại diện cho hàm getter
public class SupplierExample {

	public static void main(String[] args) {
		supply(() -> "Hi");
		supply(() -> "Hey");
		supply(() -> "Hello");
		
		Supplier<Integer> supplierInteger = () -> 50; //getter supplierInteger 50 
		System.out.println(supplierInteger.get());
		
		Supplier<String> supplierString = () -> "Soumitra";
		System.out.println(supplierString.get());
	}

	public static void supply(Supplier<?> supplier) {
		System.out.println(supplier.get());
	}

}