package com.java8.springboot.spring.sendmail;

import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class SendEmail {
//	@Autowired
//	private JavaMailSender mailSender;

	private static final String MY_EMAIL = "tiendungac99@gmail.com";

	// Replace password!!
	private static final String MY_PASSWORD = "rlqhxvegjtachhpb";

	public static String dir = System.getProperty("user.dir");

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

	public void SendSimpleEmail_Text() {
		JavaMailSender mailSender = getJavaMailSender();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("dungnguyen150699@gmail.com");
		message.setSubject("Test Simple Email");
		message.setText("Hello, Im testing Simple Email");
		mailSender.send(message);
	}

	public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) {
//		JavaMailSender mailSender = getJavaMailSender();
//		MimeMessagePreparator preparator = new MimeMessagePreparator() {
//			@Override
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//				mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
//				mimeMessage.setSubject(subject);
//				mimeMessage.setText(body);
//
//				FileSystemResource file = new FileSystemResource(new File(fileToAttach));
//				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//				helper.addAttachment("logo.jpg", file);
//			}
//		};
//		try {
//			mailSender.send(preparator);
//		} catch (MailException ex) {
//			// simply log it and go on...
//			System.err.println(ex.getMessage());
//		}
		MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();
		FileSystemResource file = new FileSystemResource(new File(fileToAttach));
		try {
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		    helper.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		    helper.setFrom("xyz@gmail.com");
//		    helper.setText("Hi");
			helper.addAttachment(file.getFilename(), file);
//		    helper.addAttachment("tesstFile", new File(fileToAttach));
			try {
				getJavaMailSender().send(mimeMessage);
			} catch (MailException ex) {
				// simply log it and go on...
				System.err.println(ex.getMessage());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment)
			throws MessagingException {

		MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("spring.email.from@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);

		getJavaMailSender().send(mimeMessage);
		System.out.println("Mail Send...");
	}

	public static void main(String[] args) throws MessagingException {
		String pathFileTxt = Paths.get(dir).resolve("logs/spring-boot-logger-2022-09-19.log").toString();
		System.out.println(pathFileTxt);
//		new SendEmail().sendEmailWithAttachment("dungnguyen150699@gmail.com", "<h1>Test Email vs Attachment</h1>", "is Body",
//				"C:\\Users\\DELL\\Pictures\\Saved Pictures\\download.jpg");
		new SendEmail().sendEmailWithAttachment("dungnguyen150699@gmail.com", "<h1>Test Email vs Attachment</h1>", "is Body",pathFileTxt);
//		new SendEmail().SendSimpleEmail_Text();
	}
}
