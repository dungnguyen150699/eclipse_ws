package com.java8.springboot.java.TryCatch_IfElse;


public class nest_trycatch_Exception {
	public static void main(String...strings ) {
		try {
			try {
			System.out.println("begin");
			throw new Exception();
			}
//			catch (Exception e) {
//				// TODO: handle exception
//				System.out.println("xxx");
//			}
			finally {
				System.out.println("xxx1");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("xxx2");
		}

	}
}
