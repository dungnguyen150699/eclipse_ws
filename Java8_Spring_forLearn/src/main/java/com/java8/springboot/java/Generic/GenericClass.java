package com.java8.springboot.java.Generic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



interface InterfaceGeneric <E,V>{
	E getAll();
	V showHello();
}


public class GenericClass implements InterfaceGeneric<List, Void>{
	public static void main(String[] args) {
		List<String> list = Arrays.asList("1","2","3");
		ModelGeneric<List<String>, Boolean,Boolean> model = new ModelGeneric(list,true,null,true);
		String str = model.toString();
		System.out.println(str);
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void showHello() {
		// TODO Auto-generated method stub
		return null;
	}

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class ModelGeneric<K, T, N> implements Serializable {
	/**
	 * Author Dũng T - Type (Kiểu dữ liệu bất kỳ thuộc Wrapper class: String,
	 * Integer, Long, Float, …) E – Element (phần tử – được sử dụng phổ biến trong
	 * Collection Framework) K – Key (khóa) V – Value (giá trị) N – Number (kiểu số:
	 * Integer, Double, Float, …) U,S,I,G, … (tùy theo kiểu của người dùng đặt) Sự
	 * thật là bạn đặt tên méo gì cũng đc tuy nhiên nên theo quy tắc trên này
	 * Genneric ko support kiểu nguyên thủy premitive
	 */
	K allowEkyc;
	T allowAdvanceEkyc;
	Boolean test;
//	E E;
	N N;
//	V V;

	public String toString() {
		return allowEkyc.toString() + allowAdvanceEkyc.toString() + test + "" + N;
	}

	// <K,V> : Nói rằng phương thức này có 2 kiểu tham số K,V
	// Phương thức trả về một đối tượng kiểu V.
	public static <K1, V> V getValue(Map<K1, V> entry) {
		V value = entry.values().stream().collect(Collectors.toList()).get(0);
		return value;
	}
	
	public static void main(String[] args) {
		Map map = new HashMap<Integer,String>();
		map.put(1, "mot");
		System.out.println(getValue(map));
	}
}

