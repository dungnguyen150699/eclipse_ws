package com.java8.springboot.java;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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

class test2{
	public static void main(String[] args) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		Date d1 = new Date();
		calendar1.setTime(d1);
		Date d2 = new Date("2022/12/21");
		calendar2.setTime(d2);
		System.out.println("xx".startsWith(""));
		System.out.println(d1.getTime() - d2.getTime() > 7*60*60*24*1000);
		System.out.println(calendar1.get(Calendar.MONTH));
	}
}


class UCLN{
	public int getUCLN2Number(int a, int b) {
		if(a==0) return b;
		else return getUCLN2Number(b%a,a);
	}
	
	public static void main(String[] args) {
		UCLN object = new UCLN();
		int ucln = object.getUCLN2Number(9, 6);
		int bcnn = 9*6/ucln;
		System.out.println("Result UCLN: " + ucln + "\n" + 
		"Result BCNN: " + bcnn);
	}
}