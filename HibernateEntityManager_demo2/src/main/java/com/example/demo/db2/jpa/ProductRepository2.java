package com.example.demo.db2.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db2.Entity.Product2;


@Repository
public interface ProductRepository2 extends JpaRepository<Product2, Integer> {

}
