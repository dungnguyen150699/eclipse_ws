package com.example.demo.Service;

import com.example.demo.Entity.OrderDetailEntity;

public interface OrderDetailService {
	OrderDetailEntity findByID(int id);
	
	OrderDetailEntity create(OrderDetailEntity od);
	
}
