package com.java8.springboot.java.access_modify.package1;

import lombok.Data;

public class Root {
	int defaultV = 5; // Default
	private int privateV = 6; // Private
	protected int protectedV = 7; // Protected
	public int publicV = 8; //Public
	
	void defaultMethod() {
		System.out.println("This Default Method");
	}
}
