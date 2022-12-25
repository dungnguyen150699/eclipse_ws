package com.java8.springboot.java.CollectionFrameWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.ListUtils;


public class ListInterface {
	public static void main(String[] args) {
		List<String> l1 = new ArrayList(Arrays.asList("1","2","3","4"));
		List<String> l2 = new ArrayList(Arrays.asList("3","2","10"));
		
		List resultList = ListUtils.subtract(l1, l2);
		System.out.println(resultList.toString());
		
		System.out.println(l1.toString());
		System.out.println(l2.toString());
	}
}
