package com.java8.springboot.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


// https://techmaster.vn/posts/36310/dependency-injection-spring-boot-commandlinerunner-interface-example
@Order(value=1)
@Component
@Slf4j
public class GETCommandLineRunner implements CommandLineRunner{

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
        log.error("ApplicationStartupRunnerOne run method Started !!");
	}

}

@Order(value=3)
@Component
@Slf4j
class ApplicationStartupRunnerOne implements CommandLineRunner {

    public void run(String... args) throws Exception {
        log.info("ApplicationStartupRunnerOne run method Started !!");
    }

}

@Order(value=2)
@Component
@Slf4j
class ApplicationStartupRunnerTwo implements CommandLineRunner {

    public void run(String... args) throws Exception {

        log.info("ApplicationStartupRunnerTwo run method Started !!");

    }

}