package com.example.demo.Entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Productdto implements Serializable{
	private int id;
	
	private String name;
	
	private byte[] img;
	
	private double price;
	
	private BigInteger count;
	
//	public Productdto (Integer id , String name , double price , BigInteger count) {
//		this.id = id;
//		this.name = name;
//		this.price = price ;
//		this.count = count;
//	}
}
