package com.java8.springboot.java.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Thread_ExtendThread extends Thread {
	public List<String> list;
	public Vector<String> vector;
	public Integer x;

	public Thread_ExtendThread(List<String> list, Vector<String> vector, int x) {
		this.list = list;
		this.vector = vector;
		this.x = x;
	}
	
	@Override
	public void run() {
			{
			try  {
//				System.out.println("ok");
				// Displaying the thread that is running
//				System.out.println("Thread " + Thread.currentThread().getId() + " is running");
				list.add(Thread.currentThread().getId() + "ArrayList---" + x); // Lỗi ở đây thì ko chạy vector ở dưới
				vector.add(Thread.currentThread().getId() + "Vector---" + x);	
				System.out.println(list.toString() + "------list");
				System.out.println(vector.toString() + "--------vector");
			} catch (Exception e) {
				// Throwing an exception
				System.out.println(e.getMessage());
			}
		}
	}
}

//Main Class You should run config to multi class in here
class Multithread {
	public static void main(String[] args) {
//     int n = 8; // Number of threads
//     for (int i = 0; i < n; i++) {
//         Thread object
//             = new Thread(new Thread_ExtendThread());
//         object.start();
//     }
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
//		List<String> list = new ArrayList();
		Vector<String> vector = new Vector<String>();
		list.add("List Synchronized Begin");
		vector.add("Vector Synchronized Begin");
		int n = 8; // Number of threads
		synchronized (vector){
			for (int i = 0; i < n; i++) {
			Thread object = new Thread_ExtendThread(list, vector, i);
			object.start();
//			new Thread_ExtendThread(list, vector).start();
			}
		}
//		synchronized(list){
//		Thread t = new Thread_ExtendThread(list,vector);
//		t.start();
//		t.stop();
//		list.add("List Synchronized end");
//		vector.add("Vector Synchronized end");
		
		// cái này sẽ ko thể in ra được vì nó đnag là đa luồng đm ở đây ngu đi chứ chả khôn hơn tý gì
//		System.out.println(list.toString());
//		System.out.println(vector.toString() + "---------xxxxxxxxxxxxx");
//		}
	}
}

class TestThread {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread 1_1", 1);
        MyThread t2 = new MyThread("Thread 1_2", 10);
        MyThread t3 = new MyThread("Thread 1_3", 100);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Table {
    static synchronized void print(String name, int n) {
        for (int i = 1; i <= 5; i++)
            System.out.println(name + ": " + i * n);
        }
}

class MyThread extends Thread {
    private String name;
    private int value;

    public MyThread(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void run() {
        Table.print(name, value);
    }
}