package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Config.Jwt.JwtAuthenticationFilter;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JwtDemoApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        // Khi chương trình chạy
        // Insert vào csdl một user.
//        User user = new User();
//        user.setUsername("loda");
//        user.setPassword(passwordEncoder.encode("loda"));
//        userRepository.save(user);
        log.trace("This is TRACE");
        log.debug("This is DEBUG");
        log.info("This is INFO");
        log.warn("This is WARN");
        log.error("This is ERROR");
//        System.out.println(user);
    }
}
