package com.example.demo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Repository.OrderDetailRepository;
import com.example.demo.Service.OrderDetailService;
import com.example.demo.error.NotFoundException;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	private OrderDetailRepository odr;
	
	@Override
	public OrderDetailEntity findByID(int id) {
		// TODO Auto-generated method stub
		OrderDetailEntity ode = odr.findById(id);
		
		if(ode==null) throw new NotFoundException("Not Found OrderDetail");
		return ode;
	}

	@Override
	public OrderDetailEntity create(OrderDetailEntity od) {
		// TODO Auto-generated method stub
		return odr.save(od);
	}

}
