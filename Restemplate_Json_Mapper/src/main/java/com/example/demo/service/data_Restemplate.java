package com.example.demo.service;
import java.net.URI;


import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.dto.Product;
//import  com.example.demo.mapper.CommonMapper;
import com.example.demo.mapper.ProductMapper;
//import org.apache.http.impl.client.HttpClientBuilder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class data_Restemplate {
	
	private final ProductMapper pm ;		
		
	@Autowired
	private RestTemplate rt;	
	
	// Mẫu request đầy đủ nhất có cả header , còn @RequestParam thì nên đưa vào url;
	public List <Product> getAll(String url) throws URISyntaxException {
		URI url1 = new URI(url);
//		RequestEntity request = new RequestEntity(HttpMethod.GET,url1);
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		header.add("Accept-Language", "en");
		RequestEntity request = new RequestEntity(new Object(), header, HttpMethod.POST , url1);
		
		ResponseEntity<ProductEntity[]> x = rt.exchange(request,ProductEntity[].class);
		return pm.toDtos(Arrays.asList(x.getBody()));
	}
	// Mẫu request đầy đủ nhất có cả header 
	public void getAll1(String url) throws URISyntaxException {
		URI url1 = new URI(url);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Accept-Language", "en");
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(new Object(), header);
		
		ResponseEntity<Object> x = rt.exchange(url1,HttpMethod.POST,httpEntity,Object.class);
//		return pm.toDtos(Arrays.asList(x.getBody()));
	}
//	Mẫu request gồm cả @RequestParam và Header thì nên đưa vào url;
	public void postFormData() throws URISyntaxException {
		URI url = new URI("url");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		// Cái map để nhét @RequestParam
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("email", "first.last@example.com");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = rt.postForEntity(url, request , String.class);
	}
}
