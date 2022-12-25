 package com.example.demo.db1.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;

import java.io.Serializable;
//
@Entity
@Table(name="product")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence",allocationSize = 1)
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public Product() {

    }

    @Override
    public String toString() {
        return "Product " +
                "id= " + id +
                " tên ='" + name + " " +
                " Mô tả =" + description;
    }

}
