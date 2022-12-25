package com.example.demo.db1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db1.Config.ScopeRequest;
import com.example.demo.db1.Entity.Product;
import com.example.demo.db1.Service.productService;

@Controller
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {
	@Autowired 
	private productService pr;
	@Autowired 
	@Qualifier(value = "x2")
	private Product x;
	
	@Autowired 
	/* Khi tiêm vào thì nó ko chạy post_construct chi khi dùng thì mới gọi ( TH dùng ScopeRequest)
		Thêm proyMode , Bởi vì chỉ tiêm đc 1 bean mà bean RequestScope lại khởi tạo lên tục
	*/
	@Qualifier(value = "component1")
	private ScopeRequest scopeRequest;
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAll(){
//		System.out.println(x.toString() == null ? "null" : x.toString());
		scopeRequest.doSomeThing();
		return ResponseEntity.status(HttpStatus.OK).body(pr.getAll());
	}
	
	@GetMapping(value="/autoinsert", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> autoinsert(){
		pr.saveProduct();
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}