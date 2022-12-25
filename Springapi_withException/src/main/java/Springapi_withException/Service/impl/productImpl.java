package Springapi_withException.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Springapi_withException.Service.productService;
import Springapi_withException.dto.productDto;

@Component
public class productImpl implements productService{

	private static List<productDto> products = new ArrayList();
	static 
	{
		products.add(new productDto("opploA37", 3500000, 100));
		products.add(new productDto("Iphon 6 plus", 1500000, 100));
		products.add(new productDto("opplo66", 3500000, 100));
		products.add(new productDto("hello", 3500000, 100));
	}
	
	@Override
	public List<productDto> findAll() {
		// TODO Auto-generated method stub
		
		return products;
	}

}
