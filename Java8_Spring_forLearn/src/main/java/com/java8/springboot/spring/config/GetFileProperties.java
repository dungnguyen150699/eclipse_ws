package com.java8.springboot.spring.config;

import java.util.logging.Level;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import com.sun.istack.logging.Logger;

import lombok.extern.slf4j.Slf4j;

@Order(value=4)
@Configuration
@PropertySource("classpath:testFile.txt")
public class GetFileProperties implements CommandLineRunner{
	private final Environment environment;
	
	String javaHomePath = System.getenv("JAVA_HOME");
	public GetFileProperties(final Environment environment) {
		this.environment = environment;
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Logger.getLogger(GetFileProperties.class).log(Level.SEVERE,
				"name: " + environment.getProperty("name") + "\n" +
				"age: " + environment.getProperty("age") + "\n"
				);
		System.out.println(javaHomePath);
	}
	
	
}
