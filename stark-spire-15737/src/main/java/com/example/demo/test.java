package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PseudoColumnUsage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

public class test {
	private byte[] x;
	
	public byte[] getX() {
		return this.x;
	}
	
	public void setX(byte []x1) {
		this.x = x1;
	}

	private ProductService ps = new ProductService();
		
	public static void main(String[] args) throws IOException {
		test t1 = new test();
		System.out.println(t1.ps.findAllProductDesc().toString());
		Product p1 = new Product();
		p1.setName("px");
		t1.ps.saveProduct(p1);
	}
}
