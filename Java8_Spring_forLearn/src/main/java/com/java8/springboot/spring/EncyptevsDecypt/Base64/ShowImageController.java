package com.java8.springboot.spring.EncyptevsDecypt.Base64;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java8.springboot.spring.EncyptevsDecypt.Product;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/spring/base64")
@Slf4j
public class ShowImageController {
//	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public String upload(Model model, 
			@RequestParam(name = "img") MultipartFile img) {
		Product p = new Product();
		System.out.println(img.getName()+"-----------"+img.getOriginalFilename());
		try {
			p.setImgByte(img.getBytes());
			p.setName(FilenameUtils.getBaseName(img.getOriginalFilename()));
			p.setTail(FilenameUtils.getExtension(img.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("product",p);
		return "showImage";
	}
	
	@RequestMapping(value="/showimage")
	public String showPage(Model model) {
		log.info("Example log info from {}", ShowImageController.class.getSimpleName());
		log.warn("Example log from {}", ShowImageController.class.getSimpleName());
		log.error("Example log trace from {}", ShowImageController.class.getSimpleName());
		return "showImage";
	}
	
}
