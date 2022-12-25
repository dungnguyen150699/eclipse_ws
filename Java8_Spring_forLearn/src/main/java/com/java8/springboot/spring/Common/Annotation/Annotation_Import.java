//package com.java8.springboot.spring.Common.Annotation;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//public class Annotation_Import {
//	public class Dog {
//	    public Dog() {
//	        System.out.println("Dog bean created!");
//	    }
//	}
//
//	// --------------------------------------------------
//	public class Cat {
//
//	    public Cat() {
//	        System.out.println("Cat bean created!");
//	    }
//	}
//	
//	@Configuration
//	public class CatConfig {
//
//	    @Bean
//	    public Cat cat() {
//	        return new Cat();
//	    }
//	}
//
//	//---------------------------
//
//	@Configuration
//	public class DogConfig {
//
//	    @Bean
//	    public Dog dog() {
//	        return new Dog();
//	    }
//	}
//}
