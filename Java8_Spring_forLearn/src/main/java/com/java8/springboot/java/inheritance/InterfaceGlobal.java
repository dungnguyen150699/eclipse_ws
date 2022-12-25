package com.java8.springboot.java.inheritance;

import java.util.Arrays;
import java.util.Date;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

// if not declare accessmodify in interface it is public(no have private)
public interface InterfaceGlobal {
	// 1 variable
	public String str= "interface";
	
	
	@NonNull
	public String println(String x);
	
	/*
	 *  in the interface yu can create 
	 *  => 1 	variable
	 *  => 2    static method
	 *  => 2    default method
	 */

}

@FunctionalInterface
interface FunctionInterface <T>{

	    int compare(T o1, T o2);

	    // vì phương thức equals là 1 phương thức của Object Cha đẻ của mọi thứ kể cả interface;
	    boolean equals(Object obj);
	    
}


class ImplementInterFaceGlobal implements InterfaceGlobal{
	
	public ImplementInterFaceGlobal() {
		// TODO Auto-generated constructor stub
		// Mọi class trong java đều được tự động kế thừa từ ObjectClass Không thì click vào từ kháo Super kia đi
		super();
	}
	
	@Override
	public String println(String x) {
		// TODO Auto-generated method stub
//		System.out.println(super.str);
		return null;
	}
	
	public static void main(String...strings ) {
		new ImplementInterFaceGlobal().println("");
		abstractA a = new abstractA("xx") {
			
			@Override
			public String println1(String x) {
				// TODO Auto-generated method stub
				return null;
			}
		};
/*		cant print by str is default
  		default u not need declare like public,private,protect
  		u cant declare variable abtract(interface ,abstract)
 * */

//		System.out.println(a.str); 
		
		System.out.println(status.exports.code);
//		System.out.println();
		ImplementInterFaceGlobal implementA = new ImplementInterFaceGlobal();
		
		System.out.println(Arrays.asList(1,0).contains(1));
	}
	
	private Date date;
	
	public void setDate(Date date) {
		this.date = date;
	}
}

enum status{
	exports(1), imports(2);
	public int code;
	status(int code){
		this.code = code;
	}
}
