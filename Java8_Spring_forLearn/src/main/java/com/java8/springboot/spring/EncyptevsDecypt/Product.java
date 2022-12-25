package com.java8.springboot.spring.EncyptevsDecypt;

import java.io.Serializable;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//MultiPartFile cant Serializable
public class Product implements Serializable {
	private byte[] imgByte;
	private String name, tail;
	

	public String getImgBase64() {
//		System.out.println(imgBase64.getOriginalFilename());
//		Scanner ca = new Scanner(System.in);
//		int x = ca.nextInt();
		String str = Base64.encodeBase64String(this.getImgByte());
//		System.out.println(str);
		return str;
	}
	
	public static void main(String[] args) {
	}
}
