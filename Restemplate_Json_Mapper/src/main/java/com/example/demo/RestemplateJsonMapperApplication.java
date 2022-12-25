package com.example.demo;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.Product;
import com.example.demo.service.data_Restemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@SpringBootApplication
public class RestemplateJsonMapperApplication implements CommandLineRunner {
	@Autowired
	private data_Restemplate dr;
	
	@Autowired
	private RestTemplate rt;	
	
	public static void main(String[] args)  {
		SpringApplication.run(RestemplateJsonMapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		testRestemplate();
//		post();
//		postVsHeader();
	}
	
		
	public void restTemplateAndJson_Gson() throws URISyntaxException, JsonProcessingException {
//		to Json 2 method
		//		Method 1 use ObjectMapper
		   	List<Product> lq = dr.getAll("https://61712f0bc20f3a001705fb37.mockapi.io/reactjs_api/products");
//		Object lq = dr.getAll("https://61712f0bc20f3a001705fb37.mockapi.io/reactjs_api/products");
		System.out.println(lq.toString());
		
		// Convert Object to Json using ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("\n"+"JSON- ObjectMapper:"+mapper.writeValueAsString(lq));
//		Result: [{"id":1,"price":1.0,"count":9,"name":"name 1","time":null}]
		
		
		//		Method 2 use Gson to Json to Object java
		Gson gs = new GsonBuilder().serializeNulls().create();
		System.out.println("\n"+"JSON- Gson:"+gs.toJson(lq));
			//		Result: [{"id":1,"price":1.0,"count":9,"name":"name 1","time":null}]
		Product[] p = gs.fromJson(mapper.writeValueAsString(lq), Product[].class);
		System.out.println(p.getClass()+ ":"+ p.toString());
		
		JSONObject json = new JSONObject(new JSONArray(gs.toJson(lq)).get(0).toString()); 
		System.out.println(json);
		String str = json.getString("name");
		System.out.println(str);
//		JSONArray ja = new JSONArray(gs.toJson(lq));
//		System.out.println(ja.get(1).toString());
//		Arrays.asList(strs).stream().forEach(str -> System.out.println(str));
	}
	
	public void testRestemplate() {
		String url = "http://localhost:8080/charging-plus/plus";
		Map map = new HashMap<String, String>();
		map.put("proHost", "150699");
		map.put("proPort", "1234");
		map.put("proUser", "dung");
		map.put("proPass", "b17dccn160");
		map.put("partyCode", "code");
		map.put("phoneNumber", "0394311365");
		map.put("money", "100000");
		map.put("addDay", "10");
		url = setParamToURL(url, map);
		Object o = rt.getForObject(url, Object.class);
		System.out.println(o.toString());
	}
	
	public String setParamToURL(String url ,Map<String,String> map) {
		int i = 0;
		for(Map.Entry<String, String> entry : map.entrySet()) {
			if(i==0) url = url+"?"+entry.getKey()+"="+entry.getValue();
			else url = url+"&"+entry.getKey()+"="+entry.getValue();
			i++;
		}
		System.out.println(url);
		return url;
	}
	
//	 Set Param với method Post
	public void post(){
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		String url = "http://localhost:8080/charging-plus/plus";
		params.add("proHost", "150699");
		params.add("proPort", "1234");
		params.add("proUser", "dung");
		params.add("proPass", "b17dccn160");
		params.add("partyCode", "code");
		params.add("phoneNumber", "0394311365");
		params.add("money", "100000");
		params.add("addDay", "10");
	    String result = rt.postForObject(url, params, String.class) ;
	    System.out.println(result);
	}
	
}
