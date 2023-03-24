package com.java8.springboot.java.access_modify.package1.child_package1;

import com.java8.springboot.java.access_modify.package1.Root;

public class ChildClass extends Root{
	
	public static void main(String[] args) {
		Root root = new Root();
		System.out.println(root.publicV);
	}
}
