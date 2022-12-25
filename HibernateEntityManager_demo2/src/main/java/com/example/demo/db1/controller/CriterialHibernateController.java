package com.example.demo.db1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db1.Entity.Product;
import com.example.demo.db1.Service.productService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/criterial")
@RequiredArgsConstructor
public class CriterialHibernateController {
	
	private final productService ps;
	
	@GetMapping(value="/find", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> find_Criterial(){
		return ResponseEntity.status(HttpStatus.OK).body(ps.criterial_Find());
	}
	
	@GetMapping(value="/find2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> find_Hibernate(){
		return ResponseEntity.status(HttpStatus.OK).body(ps.EntityManager_NativeQuery());
	}
	
}
