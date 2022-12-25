package com.java8.springboot.java.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Thread_ExtendRunable implements Runnable {
	public List<String> list;
	public Vector<String> vector;	
	public Thread_ExtendRunable(List<String> arrayList, Vector<String> vector) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.vector = vector;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// Displaying the thread that is running
			System.out.println("Thread " + Thread.currentThread().getId() + " is running");
//			list.add(Thread.currentThread().getId() + "ArrayList");
			vector.add(Thread.currentThread().getId() + "Vector");
			System.out.println("ok");
		} catch (Exception e) {
			// Throwing an exception
			System.err.println(e.getMessage());
		}
	}

}

//Main Class You should run config to multi class in here
class Multithread1 {
	public static void main(String[] args) {
//		ArrayList<String> arrayList = new ArrayList();
		// C
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		Vector<String> vector = new Vector<String>();
		list.add("List Synchronized Begin");
		vector.add("Vector Synchronized Begin");
		int n = 8; // Number of threads
//		new Thread(new Thread_ExtendRunable(list,vector));
		for (int i = 0; i < n; i++) {
			Thread object = new Thread(new Thread_ExtendRunable(list,vector));
			object.start();
		}
		list.add("List Synchronized end");
		vector.add("Vector Synchronized end");
		System.out.println(list.toString());
		System.out.println(vector.toString());
	}
}