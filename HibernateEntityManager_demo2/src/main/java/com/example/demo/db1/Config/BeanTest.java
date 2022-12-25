package com.example.demo.db1.Config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import com.example.demo.db1.Entity.Product;


@Configuration
public class BeanTest {
	
	// Bean name = value 2 cais gioong nhau
	@Bean(name= {"x1","x11","x12"})
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//	Khai báo RequestScope thì sẽ khô tiêm đc vào bean khác hay class khác cần proxyMode 
//	@RequestScope
	public Product p1() {
		return new Product("p1---------","p1---------");
	}
	
	@Bean(value= {"x2","x22","x23"})
	public Product p2() {
//		@PostConstruct // Bean function thì ko thể gị post_construct đc dùng compoent or service
		return new Product("p2---------","p2---------");
	}
}
