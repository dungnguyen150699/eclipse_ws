package com.java8.springboot.spring.Common;

import org.slf4j.Logger;
import org.springframework.util.Assert;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Log4j2  // Nên dùng cái mới 
//@Slf4j
//https://www.baeldung.com/spring-assert
public class SpringAssert {
	public static void main(String[] args) {
		try{
			Assert.isTrue(false, "throw Exception");
		}
		catch(IllegalArgumentException e) {
			log.error(e);
		}
	}
}
