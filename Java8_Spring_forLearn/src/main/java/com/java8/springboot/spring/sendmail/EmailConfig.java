package com.java8.springboot.spring.sendmail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	private static final String MY_EMAIL = "tiendungac99@gmail.com";

	// Replace password!!
	private static final String MY_PASSWORD = "b17dccn160";

	// And receiver!
//	    private static final String FRIEND_EMAIL = "yourFriend@gmail.com"; // order haved

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
//	        mailSender.setProtocol("smtp");

		mailSender.setUsername(MY_EMAIL);
		mailSender.setPassword(MY_PASSWORD);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}
