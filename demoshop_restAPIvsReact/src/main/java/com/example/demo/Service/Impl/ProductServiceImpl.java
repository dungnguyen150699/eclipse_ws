package com.example.demo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.Productdto;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import com.example.demo.error.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository pr;

	@Override
	public Page<ProductEntity> findByName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ProductEntity> page = pr.findByName(name, pageable);
		return page;
	}

	@Override
	public Page<ProductEntity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		
		return pr.findAll(pageable);
	}

	@Override
	public ProductEntity findById(int id) {
		// TODO Auto-generated method stub
		ProductEntity p = pr.findById(id);
		if(p == null) throw new NotFoundException("Not Found Product");
		return p;
	}

	@Override
	public ProductEntity save(ProductEntity p) {
		// TODO Auto-generated method stub
		return pr.save(p);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		pr.deleteById(id);
	}
	
}
