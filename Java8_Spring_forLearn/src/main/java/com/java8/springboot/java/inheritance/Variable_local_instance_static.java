package com.java8.springboot.java.inheritance;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class Variable_local_instance_static implements Serializable {
	
	// 1 Variable instance
	private Integer x ;
	
	private int intX;
		
	private String str ;
	
	private Long l;
	
	public static String str2;
	
	public Long getL() {
		return l;
	}

	public void setL(Long l) {
		this.l = l;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}
	
	public Variable_local_instance_static(Integer x,String str) {
		this.x = x;
		this.str= str;
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public Variable_local_instance_static() {};

	public static void main(String...strings ) {
		Variable_local_instance_static t = new Variable_local_instance_static();
		System.out.println(t.x + "----" + t.intX); 	// nếu là wrapper thì là null int thì = 0; 
		System.out.println(Variable_local_instance_static.str2); //static cũng giống như wrapper nếu ko khởi tạo giá trị thì nó là null
		Variable_local_instance_static.str2 = "xxx";
		System.out.println(Variable_local_instance_static.str2); // Có thể gán giá trị cho biến static
		// nhé nói chung dùng bình thường vì bộ nhó nó chỉ cố định 1 cái thôi
	
		int x;
		Integer X2;
		// System.out.println(x + "" + X2 ) ; // Nếu là local thì kiểu primitive , wrapper (Object)
		// phải đc khởi tạo giá trị khi mà đc goi đến vì khi khao báo nó sẽ ko đc ngán bất kì giá trị nào
 
	}
}

// Vào chi tiết thôi
class StaticClassExample{
	/*
	 *  Một khái niệm khác là biến static cấp bộ nhớ 1 lần 
	 *  lưu vào bộ nhớ head của java
	 *  https://viettuts.vn/java/tu-khoa-static-trong-java
	 *  https://topdev.vn/blog/tu-khoa-static-va-final-trong-java/
	 */
	
	public static int variableStatic = 10;
	public int intX;
	
	// Nest class không thể call main method đc // Vì nó ko hỗ trợ static nữa
	// Không thể @Overrive 1 phương thức static
	static class NestClass_ClassCallStatic extends StaticClassExample{
		public static int variableStatic = 11; // nó sẽ ko kế thừa , phân biệt = từ khóa super để gọi thằng cha
//		@Override
		public void main1(String[] args) { // Đây nếu sửa thành main thì sẽ lỗi
			System.out.println(super.intX);
			System.out.println(super.variableStatic);
		}
	}
	
	public static void main(String[] args) {
		
	}
}


