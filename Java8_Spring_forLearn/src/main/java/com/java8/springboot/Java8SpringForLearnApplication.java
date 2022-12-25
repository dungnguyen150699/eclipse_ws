package com.java8.springboot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.java8.springboot.spring.GETCommandLineRunner;
import com.java8.springboot.spring.Common.Annotation.Annotation_ConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(Annotation_ConfigurationProperties.class) // Thêm cái này nếu ko muốn @Config ở Pojo
@ConfigurationPropertiesScan("com.java8.springboot.spring.Common.Annotation") // Nếu có nhiều Pojo định nghĩa ConfigurationPropertie
public class Java8SpringForLearnApplication implements CommandLineRunner {
	
		//	Cách 1 dùng AnnotaionConfiguerationProperties để nhét các tham số vào 1 class
	@Autowired //Config cungx giong Component cung la Bean
	private Annotation_ConfigurationProperties configurationProperties;
		// Cách 2 Dùng Enviroment spring tự cung cấp (Ko phải cấu hình cái quái gì cả) và lấy các tham số
    @Autowired
    private Environment env;
	
	@Autowired
	private Gson gson;

	@Autowired
	private GETCommandLineRunner commandLineRunner;

	public static void main(String[] args) {
		
//		Product p = new Product();
//		p.setName("x");
//		System.out.println(gson.toJson(p));
		SpringApplication.run(Java8SpringForLearnApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		configurationProperties = new Annotation_ConfigurationProperties();
		System.out.println(configurationProperties.toString());
		System.out.println("x --------" + env.getProperty("db.password"));
	}

}

class Product{
	private String name;
	
	public void setName(String name ) {
		this.name = name;
	}
}