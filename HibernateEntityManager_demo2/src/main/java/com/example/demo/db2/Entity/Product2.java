package com.example.demo.db2.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
//
@Entity
@Table(name="product2")
@AllArgsConstructor
@Data
public class Product2 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
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

    public Product2(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public Product2() {

    }

    @Override
    public String toString() {
        return "Product2 " +
                "id= " + id +
                " tên ='" + name + " " +
                " Mô tả =" + description;
    }

}
