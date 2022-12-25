package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.Productdto;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	@Query(value = "Select from products as p where name like %:name% ", nativeQuery=true)
	Page<ProductEntity> findByName(@Param(value = "name") String name,Pageable pageable);
		
	ProductEntity findById(int id);
	
	Page<ProductEntity> findAll(Pageable pageable);
	
	@Query(value = "Delete from products where id = :id", nativeQuery=true)
	public void deleteById(@Param(value = "id") int id);
}