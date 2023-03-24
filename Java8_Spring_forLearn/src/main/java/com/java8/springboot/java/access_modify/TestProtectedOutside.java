package com.java8.springboot.java.access_modify;

import com.java8.springboot.java.access_modify.package1.Root;

public class TestProtectedOutside extends Root{
	public static void main(String[] args) {
		Root root = new Root();
		System.out.println(root.publicV);
	}
}
