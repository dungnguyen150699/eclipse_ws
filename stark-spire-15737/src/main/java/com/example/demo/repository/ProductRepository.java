package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "select * from products where name like %?1%", nativeQuery=true)
	List <Product> findByNameLike(@Param("nameProduct") String nameProduct);
	
	@Query(value = "select * from products where id = ?1", nativeQuery=true)
	Product findByID(int id);
	
	@Query(value = "select * from products order by name desc", nativeQuery=true)
	List <Product> findAllProductDesc();
	
//	@Query(value = "delete from products where id=:id", nativeQuery=true)
//	void deleteByID(@Param(value="id") int id);
//	
	@Modifying
	Page <Product> findAll(Pageable page);
	
	@Modifying
	@Query(value = "UPDATE products SET count= :count WHERE id=:id",nativeQuery = true)
	int update_Count_P(@Param("count") int count, @Param("id") int id);
}

