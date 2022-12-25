package com.java8.springboot.java.inheritance;

import org.springframework.lang.NonNull;

public abstract class abstractA {
	/*
	 * this abstract class 
	 * u can impl this class AND user key word ' super.variable 'protect, public, default';
	 * => only call super in method not static  
	 */
	
	String str1 = "abtract";
	
	abstractA(String testSuperConstructors){
		this.str1 = testSuperConstructors;
	}
	
	public abstract String println1(String x);
	
	public static void sayHelloAbstractClass() {
		System.out.println("Hello");
	}
	// Có thể overload 2 phương thức static nhưng không thể override 2 phương thức static
	public static void sayHelloAbstractClass(String x) {
		
	}
	
}

class childOfAbstractA extends abstractA implements InterfaceGlobal {

	childOfAbstractA(String testSuperConstructors) {
		/*
		 * this variable init ChildConstuctor for ParentConstructors
		 */
		super(testSuperConstructors);
		// TODO Auto-generated constructor stub
	}

	// Interface 
	@Override
	public String println(String x) {
		// TODO Auto-generated method stub
		System.out.println("interface --- " + x);
		return null;
	}

	// Abstract Class
	@Override
	public String println1(String x) {
		// TODO Auto-generated method stub
		System.out.println("abstract -- " + x);
		return null;
	}
		
	//https://www.tabnine.com/code/java/classes/org.springframework.lang.NonNull //validate method java
	@NonNull
	public Integer testNonNull() {
		return null;
	}

	public static void main(String[] args) {
		childOfAbstractA a_impl = new childOfAbstractA("oke Now Say hello Child Class");
		a_impl.println("xxx");
//		a_impl.str = "dung"; //you cant modify
//		a_impl.str1 = "dung1"; // can modify bc it not abtract
//		System.out.println(a_impl.str +"-------"+ a_impl.str1);
		
		// Still can Null
//		interfaceA interfaceA = new A_impl();
//		System.out.println(interfaceA.println("xx_heehe"));
//		System.out.println(a_impl.testNonNull());
	}
}

class childOf_AbtractClass2 extends abstractA{
	
	childOf_AbtractClass2(String testSuperConstructors) {
		super(testSuperConstructors);
		// TODO Auto-generated constructor stub
	}

	public void onlyUseSupper_InNotStaticMethod() {
		super.sayHelloAbstractClass(); // easily call method of abstract class;
	}
	
	public static void main(String[] args) {
//		super.sayHelloAbstractClass(); // cant call super in static
		childOf_AbtractClass2 child2 = new childOf_AbtractClass2("say Hello in child Class");
		child2.onlyUseSupper_InNotStaticMethod();
	}

	@Override
	public String println1(String x) {
		// TODO Auto-generated method stub
		return null;
	}
}
