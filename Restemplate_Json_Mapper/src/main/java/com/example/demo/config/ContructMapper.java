package com.example.demo.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class ContructMapper {
	  @Bean
	  public ObjectMapper objectMapper() {
	    ObjectMapper objectMapper = new ObjectMapper();

	    JavaTimeModule javaTimeModule = new JavaTimeModule();
	    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
	    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
	    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
	    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
	    objectMapper.registerModule(javaTimeModule);

	    return objectMapper;
	  }
	  
	  @Bean 
	  public RestTemplate getResTemplate() {
		  return new RestTemplate();
	  }
}
