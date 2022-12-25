package com.java8.springboot.spring.File_pdf_excel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelTest implements Serializable{
	
	private Integer id;
	
	private String key;
	
	private String value;
	
}
