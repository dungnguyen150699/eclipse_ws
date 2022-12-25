package com.example.demo.db1.Config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestScope
@Component(value = "component1")
public class ScopeRequest {
	@PostConstruct
	private void sayHello() {
		System.out.println("hello component1 Contructed (ScopeRequest)");
	}
	
	public void doSomeThing() {
		
	}
	
}
