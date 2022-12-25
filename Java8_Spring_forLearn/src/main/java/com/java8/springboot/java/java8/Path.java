package com.java8.springboot.java.java8;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Path {
	public static void main(String[] args) {
		java.nio.file.Path path = Paths.get(System.getProperty("user.dir"));
		System.out.println(path.toString());
		// Nếu truyền đường dẫn tuyệt đối vd như /xxx thì nó chỉ ra /xxx thôi
		System.out.println(path.resolve("/xxx") + "---" + path.resolve("xxxx"));
		
		// Dùng format String để chèn đường dẫn 

	}
}
