package com.java8.springboot.spring.EncyptevsDecypt.Base64;

import java.util.Base64;

public class Base64_Encode_Decode {
	
//	Java 8 Basic Base64
	public static void Base64_Basic() {
		/* Let's first encode a simple String: */
		String originalInput = "test input";
		// Encode by String != Encode Byte[]
		String encodedString1 = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println(encodedString1);
		
		byte[]encodedString2 = Base64.getEncoder().encode(originalInput.getBytes());
		System.out.println(encodedString2.toString());
		/* Let's now decode that String back to the original form: */

		byte[] decodedBytes = Base64.getDecoder().decode(encodedString2);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
	}
	
//	Java 8 Base64 Encoding Without Padding
	/*
	 * In Base64 encoding, the length of an output-encoded String must be a multiple of three. 
	 * The encoder adds one or two padding characters (=) at the end of the output as needed in order to meet this requirement.
	 */
	public static void Base64_Encode_Without_Padding() {
		
	}
	
	public static void main(String[] args) {
		Base64_Basic();
	}
}
