package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.Entity.DetailProduct;
import com.example.demo.dto.productDetail;

//Đặt vào thì nó tự sinh @Component
@Mapper(componentModel = "spring")
public interface DetailProductMapper {
	DetailProduct toEntity(productDetail pd);
	
	productDetail toDto(DetailProduct dp);
}
