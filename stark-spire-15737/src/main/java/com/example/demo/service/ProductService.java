package com.example.demo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class ProductService {
	public static List<Product> listStatic = new ArrayList<>();

	public ProductService(){
	}

	@Autowired
	private ProductRepository productRepo;
	
	public Page<Product> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 6;
		
		Pageable pageable = PageRequest.of(pageNum-1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending():
					Sort.by(sortField).descending());
		
		return productRepo.findAll(pageable);
	}
	
	public List<Product> findAllProductDesc() {
		List <Product> listProduct = productRepo.findAllProductDesc();
		int a = 1;
		int b = 2;
		for(int i=1 ; i<10 ; i++){
			b = b+a;
		}

		return listProduct;
	}
	
	public List<Product> findByNameLike(String nameProduct){
		List <Product> listProduct = productRepo.findByNameLike(nameProduct);
		return listProduct;
	}
	
	public Product findbyID(int id) {
		Product p = productRepo.findByID(id);
		List<OrderDetail> listOD = p.getOrderDetails();
		
		System.out.println("PRODUCT-----:" + p.toString());
		System.out.println(listOD.size() > 0 ? "ORDERDETAIL-----:" + listOD.get(0).toString() : "ORDERDETAIL-----:" + "empty OrderDetail List" );
		return p;
	}
	
	public void saveProduct(Product p) {
		productRepo.save(p);
		listStatic.add(p);
	}
	
	public void deleteById(int id) {
		productRepo.deleteById(id);
	}
	
//	@Transactional
	public void loopSave() {
		for(int i = 1 ; i<=3 ;i++) {
			Product p = new Product();
			p.setId(100000);
			p.setName("dung"+i);
			if(1==1) {
				p.getOrderDetails();
				p.getCategory();
			}
			saveProduct(p);
			throw new RuntimeException();
		}
		productRepo.update_Count_P(1002,1);
//		throw new RuntimeException();
	}
	
//	@Transactional(propagation = Propagation.REQUIRED)
	public void callLoopSave() {
		loopSave();
	}

	public List<Product> getStaticList(){
		return listStatic;
	}
}
