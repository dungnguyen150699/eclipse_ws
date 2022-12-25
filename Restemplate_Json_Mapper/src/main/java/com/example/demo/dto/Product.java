package com.example.demo.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{
	private Integer id;
	
	private double price;
	
	private long count;
	
	private String name;
	
	private Timestamp time;
	private String xx;
	
	private List<productDetail> pd;
}
