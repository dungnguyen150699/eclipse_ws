package com.example.demo.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Entity.OrderEntity;

public interface OrderService {
	
	public boolean addOrder(OrderEntity od) throws SQLException ;
	
	public Page<OrderEntity> findAll(Pageable pageable);
}
