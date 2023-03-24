package com.java8.springboot.java.access_modify.package2;

import com.java8.springboot.java.access_modify.package1.Root;

public class TestProtected extends Root{
	
	public TestProtected() {
		super();
	}

	public void test() {
		System.out.println(super.protectedV);
	}
	
	public static void main(String[] args) {
		Root root = new Root();
		TestProtected t = new TestProtected();
		System.out.println(t.protectedV);
		System.out.println(root.publicV);
	}

}
