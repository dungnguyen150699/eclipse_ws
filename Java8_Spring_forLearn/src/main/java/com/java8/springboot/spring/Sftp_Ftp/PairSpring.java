package com.java8.springboot.spring.Sftp_Ftp;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.data.util.Pair;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PairSpring {
//	https://www.baeldung.com/java-pairs
	
	public static Pair initPair() {
		Pair<Integer, String> pair = Pair.of(1, "One");
		pair.getFirst();
		pair.getSecond();
		return pair;
	}
	
//	public static SimpleEntry simpleEntry() {
//		AbstractMap.SimpleEntry<Integer, String> entry 
//		  = new AbstractMap.SimpleEntry<>(1, "one");
//		Integer key = entry.getKey();
//		String value = entry.getValue();
//		return entry;
//	}
	
	public static void simpleHandleEntry() {
		HashMap hm = new HashMap();
	      // Dat cac phan tu vao map
	      hm.put("Zara", new Double(3434.34));
	      hm.put("Mahnaz", new Double(123.22));
	      hm.put("Ayan", new Double(1378.00));
	      hm.put("Daisy", new Double(99.22));
	      hm.put("Qadir", new Double(-19.08));

	      // Lay mot set cac entry
	      Set set = hm.entrySet();
	      // Lay mot iterator
	      Iterator i = set.iterator();
	      // Hien thi cac phan tu
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	}
	
	// Cai nay cua Apachecommon
//	public static ImmutablePair immutablePair() {
//		ImmutablePair<Integer, String> pair = new ImmutablePair<>(2, "Two");
//		Integer key = pair.getKey();
//		String value = pair.getValue();
//	}
		
	public static void main(String[] args) {
		
	}	
}
