package com.java8.springboot.java.access_modify.package1;


public class ChildClassInPackage extends Root {
	
	public static void main(String[] args) {
		Root root = new Root();
		System.out.println(root.publicV);
		System.out.println(root.defaultV);
		System.out.println(root.publicV);
	}

}
