package com.java8.springboot.spring.File_pdf_excel;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

// Thư viên common.io
public class FileNameUtils {
	/*
	 	file.separator
		java.specification.version
		java.vm.version
		java.class.path
		java.vendor
		java.class.version
		os.arch
		java.compiler
		line.separator
		java.version
		java.vendor.url
		os.name
	 */
	public final String dir = System.getProperty("user.dir");
	
	// File extends giúp bạn có thể lấy tên file, đuồi file , và nhiều thứ khác hay ho
	public static void main(String[] args) throws IOException {
		File file = new File("file.txt");
		if(!file.exists()) file.createNewFile();
//		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		System.out.println(file.getName());
		String fileExtension = FilenameUtils.getExtension(file.getName());
		System.out.println(fileExtension);
		
		System.out.println(FilenameUtils.getBaseName(file.getName()) + "-----" + FilenameUtils.getName(file.getName()));
	}
}
