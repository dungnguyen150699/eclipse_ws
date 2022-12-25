package com.example.demo.mapper.impl;

import com.example.demo.Entity.DetailProduct;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.dto.Product;
import com.example.demo.dto.productDetail;
import com.example.demo.mapper.DetailProductMapper;
import com.example.demo.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-18T14:55:00+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private DetailProductMapper detailProductMapper;

    @Override
    public Product toDto(ProductEntity product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setId( product.getId() );
        product1.setPrice( product.getPrice() );
        product1.setCount( product.getCount() );
        product1.setName( product.getName() );
        product1.setTime( product.getTime() );
        product1.setPd( detailProductListToproductDetailList( product.getPd() ) );

        return product1;
    }

    @Override
    public Product objecttoDto(Object product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        return product1;
    }

    @Override
    public List<Product> toDtos(List<ProductEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( entity.size() );
        for ( ProductEntity productEntity : entity ) {
            list.add( toDto( productEntity ) );
        }

        return list;
    }

    @Override
    public List<Product> objecttoDtos(List<Object> entity) {
        if ( entity == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( entity.size() );
        for ( Object object : entity ) {
            list.add( objecttoDto( object ) );
        }

        return list;
    }

    protected List<productDetail> detailProductListToproductDetailList(List<DetailProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<productDetail> list1 = new ArrayList<productDetail>( list.size() );
        for ( DetailProduct detailProduct : list ) {
            list1.add( detailProductMapper.toDto( detailProduct ) );
        }

        return list1;
    }
}
