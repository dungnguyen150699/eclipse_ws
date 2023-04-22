package com.example.elasticsearch.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Document(indexName = "products")
public class Product implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Date, name = "update_date")
    private Date date;

    
   // Getter and Setter
}