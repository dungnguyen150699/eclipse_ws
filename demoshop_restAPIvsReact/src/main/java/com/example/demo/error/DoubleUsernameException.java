package com.example.demo.error;

public class DoubleUsernameException extends RuntimeException{
	
	private final static long serialVersionID = 1L ;
	
	public DoubleUsernameException(String s) {
		super(s); // hàm khởi tạo constructer lớp cha có tham số s;
	}
}
