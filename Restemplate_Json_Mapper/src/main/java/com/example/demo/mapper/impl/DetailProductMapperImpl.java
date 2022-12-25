package com.example.demo.mapper.impl;

import com.example.demo.Entity.DetailProduct;
import com.example.demo.dto.productDetail;
import com.example.demo.mapper.DetailProductMapper;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-18T14:54:59+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class DetailProductMapperImpl implements DetailProductMapper {

    @Override
    public DetailProduct toEntity(productDetail pd) {
        if ( pd == null ) {
            return null;
        }

        DetailProduct detailProduct = new DetailProduct();

        return detailProduct;
    }

    @Override
    public productDetail toDto(DetailProduct dp) {
        if ( dp == null ) {
            return null;
        }

        productDetail productDetail = new productDetail();

        return productDetail;
    }
}
