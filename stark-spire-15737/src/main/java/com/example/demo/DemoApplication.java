package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import java.util.List;

//bỏ Spring Security autoConfig cái này nếu cáu hình tư động thôi còn app này vẫn có Sercurity
@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) 
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	private ProductService ps;
	@Autowired
	private UserService us;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		ps.loopSave();
//		ps.callLoopSave();

		// Test static work vs Bean
		Product p = new Product();
		p.setName("p1");
		Product p2 = new Product();
		p.setName("p2");
		ps.saveProduct(p);
		ps.saveProduct(p2);
		List<Product> test = us.getStatic();
		System.out.println(test.toString());
		// apparent! it work
	}

}
