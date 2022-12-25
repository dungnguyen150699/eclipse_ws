package com.java8.springboot.java;

import org.apache.commons.lang3.StringUtils;

public class CompareStringUTF8 {
	public static void main(String[] args) {
//		if ("dũng".contentEquals("dung"))  // ko dc
		System.out.println(compareStripAccent("dũng","dung")); // đc này
		System.out.println(compareStripAccent("Cái này đc nhé bạn","Cai nay dc nhe ban")); // Không được nhé
	}
	
	 public static int compareStripAccent(String a, String b) {
		    return StringUtils.stripAccents(a).compareTo(StringUtils.stripAccents(b));
		}
}
