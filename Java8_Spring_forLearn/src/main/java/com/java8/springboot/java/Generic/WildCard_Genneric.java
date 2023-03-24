package com.java8.springboot.java.Generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WildCard_Genneric {
	public static void main(String[] args) {
		
	}
}

/*
 * Upper Bounded Wildcards: 
	These wildcards can be used when you want to relax the restrictions on a variable.
	 For example, say you want to write a method that works on List < Integer >, List < Double >, and List < Number >, you can do this using an upper bounded wildcard. 
	
	To declare an upper-bounded wildcard, use the wildcard character (‘?’), followed by the extends keyword, followed by its upper bound. 
	
	public static void add(List<? extends Number> list)
	
 */
class WildcardDemo_UpperWildcard {
    public static void main(String[] args)
    {
 
        // Upper Bounded Integer List
        List<Integer> list1 = Arrays.asList(4, 5, 6, 7);
 
        // printing the sum of elements in list
        System.out.println("Total sum is:" + sum(list1));
 
        // Double list
        List<Double> list2 = Arrays.asList(4.1, 5.1, 6.1);
 
        // printing the sum of elements in list
        System.out.print("Total sum is:" + sum(list2));
    }
 
    private static double sum(List<? extends Number> list)
    {
        double sum = 0.0;
        for (Number i : list) {
            sum += i.doubleValue();
        }
 
        return sum;
    }
}

/*
 * 	2. Lower Bounded Wildcards: 
	It is expressed using the wildcard character (‘?’), followed by the super keyword, followed by its lower bound: <? super A>. 
	Syntax: Collectiontype <? super A>
	
 */
class WildcardDemo_LowerWildcard {
    public static void main(String[] args)
    {
        // Lower Bounded Integer List
//        List<Integer> list1 = Arrays.asList(4, 5, 6, 7);
    	List<Integer> list1 = new ArrayList(Arrays.asList(4, 5, 6, 7));
//        list1.add(23);
 
        // Integer list object is being passed
//        printOnlyIntegerClassorSuperClass(list1);
        addNumbers(list1);
 
        // Number list
//        List<Number> list2 = Arrays.asList(4, 5, 6, 7);
 
        // Integer list object is being passed
//        printOnlyIntegerClassorSuperClass(list2);
//        addNumbers(list2);
    }
 
    public static void printOnlyIntegerClassorSuperClass(
        List<? super Integer> list)
    {	
    	list.add(12);// => throw java.lang.UnsupportedOperationException
//    	list.set(0,12); // Chỉ có thể sử dụng phương thức này ???? ==> Bởi vì dùng cái Arrays.asList nên List là cố định do 2 annotation này @SafeVarargs,@SuppressWarnings("varargs")
        System.out.println(list);
    }
    
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        System.out.println(list);
    }
    
}

class TestLowerWildCard {
    class Angel {
    	String a = "Angel";
    	@Override
		public	String toString() {
    		return a;
    	}
    }
    class Person extends Angel {
    	String p = "Person";
    	@Override
		public String toString() {
    		return p;
    	}
    }
    class Employee extends Person {
    	String e = "Employee";
    	@Override
		public	String toString() {
    		return e;
    	}
    }
    
    public void insertElements(List<? super Person> list){
        list.add(new Person());
        list.add(new Employee());
//        list.add(new Angel());
        System.out.println(list.toString());
    }
    
	public static void main(String[] args) {
		TestLowerWildCard test = new TestLowerWildCard();
		List<Person> list = new ArrayList<>();
		Employee e = test. new Employee();
		list.add(e);
		test.insertElements(list);
		
		
	}
}


class TestWildCard{
	public List<? extends Collection<Integer>> ls = new ArrayList();
	
	public static void main(String[] args) {
		List <Integer> ls1 = new ArrayList<Integer>();
		Set <Integer> st = new HashSet<Integer>();
		ls1.add(1);
		st.add(2);
		TestWildCard tCard = new TestWildCard();
		Iterator<Integer> iterator = ls1.iterator();

//		tCard.ls.addAll(ls1);
//		tCard.ls.addAll(st);
	}
}








