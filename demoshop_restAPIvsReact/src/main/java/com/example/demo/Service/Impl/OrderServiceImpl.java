package com.example.demo.Service.Impl;

import java.math.BigInteger;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Entity.OrderEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository or;
	@Autowired
	private ProductRepository pr;
	
	@Override
	@Transactional
	public boolean addOrder(OrderEntity od) throws SQLException {
		// TODO Auto-generated method stub
		boolean re = true;
		or.save(od);
		od.setId(or.LastID_OrderDetail());
		System.out.println(od.getId());
			for (OrderDetailEntity i : od.getOrderDetails()) {
				i.setOrder(od);
				ProductEntity p = i.getProduct();
				p.setCount(i.getProduct().getCount().divide(BigInteger.valueOf(i.getCount().intValue()) ));
				pr.save(i.getProduct());
			}
		return re;
	}

	@Override
	public Page<OrderEntity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		
		return or.findAll(pageable);
	}

}
