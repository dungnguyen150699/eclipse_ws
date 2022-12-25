package com.example.demo.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.Entity.DetailProduct;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.dto.Product;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DetailProductMapper.class})
public interface ProductMapper {
	
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//  Product toEntity(ContactDTO contactDTO);

  
//  @Mapping(Source, Tagert)
  Product toDto(ProductEntity product);
  
  Product objecttoDto(Object product);

  @IterableMapping(elementTargetType = Product.class)
  List<Product> toDtos(List<ProductEntity> entity);
  
  @IterableMapping(elementTargetType = Product.class)
  List<Product> objecttoDtos(List<Object> entity);

//  List<Contact> toEntitys(List<ContactDTO> dto);
}