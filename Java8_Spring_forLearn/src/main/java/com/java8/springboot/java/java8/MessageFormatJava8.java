package com.java8.springboot.java.java8;

import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.Assert;

public class MessageFormatJava8 {
	public static void main(String[] args) {
		// Cais nayf nos gioonsg String format nhưng mà nó tự động chỉnh sửa string
		String text = 
			     java.text.MessageFormat.format("You''re about to delete {0} rows.", 5);
		System.out.println(text);
		
		// Đây là một phương pháp không yêu cầu chỉnh sửa mã và hoạt động bất kể số lượng ký tự.
		// Bời vì nó là thông điệp nên nó tự loại bỏ 1 số cái. Thêm ' ở đầu ký tự đó để nó hiện thị đầy đủ
		String text2 = 
				  java.text.MessageFormat.format(
				    "You're about to delete {0} rows.".replaceAll("'", "''"), 5);
		System.out.println(text2); // Result = "You're about to delete {0} rows."
		
		// Raw text
//		System.out.println(Assert.("You're about to delete {0} rows.");
		
//		String text ="tin;dung;;trang";
//		System.out.println(text.contains(""));
//		String []texts = text.split(";");
//		System.out.println(text.length());
//		for(String str : texts) {
//			System.out.println(str);
//			if(str.isEmpty()) System.out.println("xxx");
//		}
	}
}

class StringFormat{

	void arrayTest(String ...strings) {
		System.out.println(strings.toString());
	}
	
	public static void main(String[] args) {
		String str1 = " %s Hello %s";
		String str2 = "%s Hello";
//		String []strList = Arrays.asList("A").toArray(new String[0]);
		String []strList = {"x","x2"};
		List<String> xx = Arrays.asList("A","B");
		System.out.println(String.format(str1, Arrays.asList("A","B").toArray()));
		System.out.println(String.format(str2, Arrays.asList("A").toArray()));
		System.out.println(String.format(str2, strList));
		System.out.println(String.format(str1, "A","B"));

		System.out.println(String.format(str1,xx.toArray()));
//		StringFormat sf = new StringFormat();
//		sf.arrayTest("1","2"); 
//		sf.arrayTest(Arrays.asList("1","2").toArray(new String[0]));
//		sf.arrayTest(Arrays.asList("1","2").toArray(String[] :: new));
	}
}

