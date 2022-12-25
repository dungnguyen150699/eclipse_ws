package com.example.demo.Config.Aop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.apache.bcel.classfile.Signature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletRequestHandledEvent;


@Aspect
@Component
public class WriteLogAspect {
	private final String CURRENTDIR = Paths.get(System.getProperty("user.dir")).toString();
	
	@Value("${dirLogFile}")
	private String dirLogFile;
	
	// all Class in package dao
	@Pointcut("execution(* com.example.demo.controller.Admin.*.*(..))")
	private void writeAllClass() {
		
	}
	
//	@Pointcut("@within(com.ldt.demospringaop.aspect.TrackTime) || @annotation(com.ldt.demospringaop.aspect.TrackTime)")
//	private void callAnnotationLogging() {}
	
	//joinPoint.getArg , .proceed (result) ,getSignature (method name)
	@Around("writeAllClass()")
	public Object writeLogAspect_DAO(ProceedingJoinPoint joinPoint) throws Throwable {
		String str = this.getDirName();
		System.out.println(str);
		
		//Lấy tên file
		MethodSignature sign = (MethodSignature) joinPoint.getSignature();
		StringBuilder sb = new StringBuilder("");
		String fileName = str+ "\\" + sign.getDeclaringType().getSimpleName() + ".txt";
//		System.out.println(fileName);
		
		// Tạo dir cha : mylog
		File file = new File(fileName);
		if(!file.getParentFile().exists()) file.getParentFile().mkdir();
		if(!file.exists()) file.createNewFile();
		
		sb.append("Class:" + sign.getDeclaringType().getSimpleName() + "\n");
		// Http 
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
		HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
		sb.append("\t Url: " + httpServletRequest.getRequestURI() + "\n" );
		sb.append("\t Method Name: " + sign.getName() + "\n");
		// Ghi file
		sb.append("\t Object Agurment: \n"+
		Arrays.stream(joinPoint.getArgs())
		.map(o -> "\t\t" + o.toString() + ";")
		.collect(StringBuilder::new , StringBuilder::append , StringBuilder::append)
		.toString()
		);		
		sb.append("\n");
		FileOutputStream fos = new FileOutputStream(file,true);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		osw.write(sb.toString());
		osw.flush();
		osw.close();
		Object responseObject = null;
	       try {
	            responseObject = joinPoint.proceed();
	        } catch (Exception e){
//	            System.out.println(e.getMessage());
	        }
//	       System.out.println(responseObject);
	       return responseObject;
	}
	
	private String getDirName() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formatter.format(date);
		String str = String.format(this.dirLogFile,this.CURRENTDIR,strDate);
//		System.out.println(str);
		return str;
	}
}
