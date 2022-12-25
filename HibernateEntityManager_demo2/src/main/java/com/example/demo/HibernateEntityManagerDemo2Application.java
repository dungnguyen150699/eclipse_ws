package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

import com.example.demo.db1.Entity.Product;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@SpringBootApplication
@EntityScan("com.example.demo.db2.Entity")
public class HibernateEntityManagerDemo2Application implements CommandLineRunner {
	
	@Autowired // Sẽ không lấy đc vì ko có request nào đc khởi tạo sập luôn app
	@Qualifier(value = "x11")
	private Product x1;

	@Autowired
	@Qualifier(value = "x22")
	private Product x2;
	
	@Autowired
	ApplicationContext context;
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HibernateEntityManagerDemo2Application.class, args);
		// Xem tat ca ten giong nhau
		System.out.println(Arrays.asList(context.getAliases("x1".toString()).clone()).toString());
		System.out.println(Arrays.asList(context.getAliases("x2".toString()).clone()).toString());
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println(x1.toString());
		System.out.println(x2.toString());
	}

}
