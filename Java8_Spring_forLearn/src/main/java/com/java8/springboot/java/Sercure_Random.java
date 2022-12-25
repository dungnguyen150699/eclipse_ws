package com.java8.springboot.java;

import java.security.SecureRandom;
import java.util.Scanner;

public class Sercure_Random {
	/**
	   * SecureRandom is preferred to Random.
	   */
	private static SecureRandom random = new SecureRandom();
	
	public String RandomString() {
//		Scanner ca = new Scanner(System.in);
		int length = 10;
		
	    String symbol = "-/.^&*_!@%=+>)";
	    String capLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String smallLetter = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";

	    String finalString = capLetter + smallLetter + numbers + symbol;
	    char[] password = new char[length];

	    for (int i = 0; i < length; i++) {
	      password[i] = finalString.charAt(random.nextInt(finalString.length())); //origin
//	    	password[i] = finalString.charAt(random.ne);
	    }
	    return password.toString();
	}
	
	public static void main(String[] args) {
		Sercure_Random sr = new Sercure_Random();
		System.out.println(sr.RandomString());
	}
}
