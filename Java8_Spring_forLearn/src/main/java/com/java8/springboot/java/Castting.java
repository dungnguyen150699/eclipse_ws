package com.java8.springboot.java;

//https://gpcoder.com/2406-co-che-upcasting-va-downcasting-trong-java/
public class Castting {

	public static void main(String[] args) {
		Dog d = null;
		d = new Dog();
		System.out.println("xxxx");
		System.out.println("xx" + d);
		// TH1 down casting error
		Animal a = new Animal();
		if(a instanceof Dog){ // TH1
			System.out.println("ok");
			d = Dog.class.cast(a);
		}
		// TH2 down casting ok
		a = new Dog(); // Chuyển thể hiện lớp cha thành lớp con túc là instance vẫn là Dog
		if(a instanceof  Dog){
			System.out.println("ok");
			d = Dog.class.cast(a);
		}
	}
	public void a1() {}
}

class Animal{
	public int a1 = 1;
	public void x1() {}
	public static void main(String[] args) {
		Dog d = new Dog();
		System.out.println("xxxx");
		System.out.println("xx" + d);
	}
}

class Dog extends Animal{
	public int d1 = 2;
	public void x() {}
}


