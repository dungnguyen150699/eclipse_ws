package com.java8.springboot.java.CollectionFrameWork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ThreadSafeVector_ThreadNoSafeOther {

	public static void main(String[] argv) throws Exception {
		try {
			// creating object of List<String>
			List<String> vector = new ArrayList<String>();
			// populate the vector
			vector.add("A");
			vector.add("B");
			vector.add("C");
			vector.add("D");
			vector.add("E");

			// printing the Collection
			System.out.println("Collection : " + vector);

			// getting the synchronized view of Collection
			Collection<String> c = Collections.synchronizedCollection(vector);
			// printing the Collection
			System.out.println("Synchronized view" + " of collection : " + c);
			
//			Output
//			Collection : [A, B, C, D, E]
//			Synchronized view of collection : [A, B, C, D, E]
		}

		catch (IndexOutOfBoundsException e) {
			System.out.println("Exception thrown : " + e);
		}
	}

}
