package com.example.demo.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable{
	private Integer id;
	
	private double price;
	
	private long count;
	
	private String name;
	
	private Timestamp time;
	
	private List<DetailProduct> pd;
}