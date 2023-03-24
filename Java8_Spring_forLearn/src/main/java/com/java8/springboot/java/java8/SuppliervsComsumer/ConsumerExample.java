package com.java8.springboot.java.java8.SuppliervsComsumer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

public class ConsumerExample {

	public static void main(String[] args) {
		List<String> greets = Arrays.asList("Hi", "Hey", "Hello");

		Consumer<String> consumer = new Consumer<String>() { // annonyous class
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};

		greets.forEach(consumer);

		System.out.println("===============================");

		// inner class
		ConsumerExample ce = new ConsumerExample();
		ConsumerExample.User user = ce.new User("Soumitra");
		Consumer<User> updateName = u -> u.setName("Roytuts");
		updateName = u -> u.setName("Nguyễn Tiến Dũng1");
		
		updateName.accept(user); //accept đánh giấu là consumer cuối cùng nên là bạn có thêm NTD vào sau cũng ko ăn thua gì cả
		updateName = u -> u.setName("Nguyễn Tiến Dũng2");

		System.out.println(user.getName());
	}
	
	@Getter
	@Setter
	public class User{
		private String name;
		public User(String name) {
			this.name = name;
		}
		
	}
}