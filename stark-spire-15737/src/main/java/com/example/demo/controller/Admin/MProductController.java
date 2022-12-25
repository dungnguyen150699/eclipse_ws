package com.example.demo.controller.Admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;


@Controller
@RequestMapping(value="/admin/product")
public class MProductController {
	@Autowired 
	private ProductService ps;
//	----Show
	@RequestMapping(value="")
	public String manageProduct(Model model) {
		model.addAttribute("listProduct", ps.findAllProductDesc());
		return "ADMIN/product";
	}
	
	@RequestMapping(value="/insertForm")
	public String insertForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("mes", "insert");
		return "ADMIN/formProduct";
	}
	
	@RequestMapping(value="/update/{id}")
	public String updateForm(Model model,@PathVariable(name="id") int id) {
		Product p = ps.findbyID(id);
		model.addAttribute("product", p);
		model.addAttribute("mes", "update");
		return "ADMIN/formProduct";
	}
//	---- action
	
	@RequestMapping(value = "/addProduct")
	public String addProduct(Model model,
			@RequestParam(name="name") String name,
			@RequestParam(name="img") MultipartFile img,
			@RequestParam(name="price") double price,
			@RequestParam(name="count") int count
			) {
		model.addAttribute("message", "Upload success");
		System.out.println("----------");
		try {
			Product product_entity = new Product();
			product_entity.setCount(count);
			product_entity.setName(name);
			product_entity.setPrice(price);
			product_entity.setImg(toByteWrapper(img.getBytes()));
			ps.saveProduct(product_entity);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Upload failed");
		}
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value="/update")
	public String updateProduct(Model model,
			@RequestParam(name="id") Long id,
			@RequestParam(name="name") String name,
			@RequestParam(name="img") MultipartFile img,
			@RequestParam(name="price") double price,
			@RequestParam(name="count") int count) throws IOException {
		Product product_entity = new Product();
		product_entity.setCount(count);
		product_entity.setId(id);
		product_entity.setName(name);
		product_entity.setPrice(price);
		product_entity.setImg(toByteWrapper(img.getBytes()));
		ps.saveProduct(product_entity);
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteProduct(Model model,@PathVariable(name="id") int id) {
		ps.deleteById(id);
		return "redirect:/admin/product";
	}


	public Byte[] toByteWrapper(byte [] bytePrimitive){
		Byte []byteWrapper = new Byte[bytePrimitive.length];
		for(int i=0 ; i<byteWrapper.length ; i++){
			byteWrapper[i] = bytePrimitive[i];
		}
		return byteWrapper;
	}
}
