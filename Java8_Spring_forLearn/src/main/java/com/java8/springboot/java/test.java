package com.java8.springboot.java;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		Dog d = null;
		d = new Dog();
		System.out.println("xxxx");
		System.out.println("xx" + d);
	}
}


class test1{
	public static void main(String[] args) {
        String pattern1 = "HH:mm:ss";
        String pattern2 = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern1);
        System.out.println(sdf.format(new Date()) + " ngày " + sdf.format(new Date()));
	}
}