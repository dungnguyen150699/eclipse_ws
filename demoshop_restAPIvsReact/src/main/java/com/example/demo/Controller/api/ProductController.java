package com.example.demo.Controller.api;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.Productdto;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import com.example.demo.error.BadRequestException;

//@Controller
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*")
public class ProductController {
	@Autowired ProductService ps;
	
    @PostMapping(value="/create",consumes = { "multipart/form-data" })
    public ResponseEntity<ProductEntity> create(
    		@RequestParam(name = "img") MultipartFile img,
    		@RequestParam(name = "name")String name,
    		@RequestParam(name = "price") double price,
    		@RequestParam(name = "count") BigInteger count,
    		BindingResult bindingResult,
    		HttpServletRequest request
    		) throws IOException{
    	ProductEntity pe = new ProductEntity();
    	pe.setName(name);
    	pe.setPrice(price); 
    	pe.setCount(count);
    	pe.setImg(img.getBytes());
    	if(bindingResult.hasErrors()) {
    		System.out.println(bindingResult.getFieldError().getDefaultMessage()+"-------------");
    		 throw new BadRequestException(bindingResult.getFieldError().getDefaultMessage());
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).body(ps.save(pe));
    }
    
    @PatchMapping(value="/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> update(@PathVariable(name="id") int id,@RequestBody ProductEntity request){
    	request.setId(id);
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(ps.save(request));
    }
    
    @GetMapping(value="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductEntity>> getAll(Pageable pageable){
    	Page<ProductEntity> page = ps.findAll(pageable);
    	return ResponseEntity.status(HttpStatus.OK).body(page);
    }
    
    @GetMapping(value="/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductEntity>> findByName(@RequestParam(name="name")String name,Pageable pageable){
    	return ResponseEntity.status(HttpStatus.OK).body(ps.findByName(name, pageable));
    }
    
    @GetMapping(value="/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> getByID(@PathVariable(name="id")int id){
    	return ResponseEntity.status(HttpStatus.OK).body(ps.findById(id));
    }
    
    @DeleteMapping(value="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> delete(@PathVariable(name="name")int id){
    	ps.deleteById(id);
    	return ResponseEntity.status(HttpStatus.OK).body("Deleted");    
    }
}
