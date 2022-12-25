package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.Productdto;

public interface ProductService {
	public Page<ProductEntity> findByName(String name,Pageable pageable);
	
	public Page<ProductEntity> findAll(Pageable pageable);
	
	public ProductEntity findById(int id);
	
	public ProductEntity save(ProductEntity p);
	
	public void deleteById(int id);
}
