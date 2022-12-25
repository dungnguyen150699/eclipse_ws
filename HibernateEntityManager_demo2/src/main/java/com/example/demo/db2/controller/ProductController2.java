package com.example.demo.db2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.db1.Entity.Product;
import com.example.demo.db2.Entity.Product2;
import com.example.demo.db2.Service.productService2;

@Controller
@RequestMapping("/product2")
@CrossOrigin(origins = "*")
public class ProductController2 {
	@Autowired 
	private productService2 pr;
	@Autowired 
	@Qualifier(value = "x2")
	private Product x;
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product2>> getAll(){
		System.out.println(x.toString() == null ? "null" : x.toString());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("ContentType:"+request.getContentType()+"\n Method:"+request.getMethod());
        return ResponseEntity.status(HttpStatus.OK).body(pr.getAll());
	}
	
	@GetMapping(value="/autoinsert", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> autoinsert(HttpServletRequest request){
		pr.saveProduct();
		System.out.println(request.getMethod()+"---"+request.getContextPath());
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}