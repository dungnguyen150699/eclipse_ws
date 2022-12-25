package com.example.demo.db1.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db1.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
