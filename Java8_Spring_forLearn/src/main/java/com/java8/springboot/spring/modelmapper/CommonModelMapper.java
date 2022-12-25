package com.java8.springboot.spring.modelmapper;

import org.modelmapper.convention.MatchingStrategies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;


//model mapper làm được hết
// Cái này tương tự modelmapper
//https://stackoverflow.com/questions/19763278/how-to-use-beanutils-copyproperties
public class CommonModelMapper {
	
	public static ModelMapper modelMapper() {
		// Tạo object và cấu hình
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	public static void main(String[] args) {
//		ProductEntity pEntity0 = new ProductEntity(1,"p1",123,null);
//		OrderDetailEntity orderDetailEntity0 = new OrderDetailEntity(1,213,pEntity0);
//		OrderDetailEntity orderDetailEntity1 = new OrderDetailEntity(2,213,pEntity0);
//		List<OrderDetailEntity> list = new ArrayList<OrderDetailEntity>();
//		list.add(orderDetailEntity0);
//		list.add(orderDetailEntity1);
//		ProductEntity pEntity = new ProductEntity(1,"p1",123,list);
//		Productdto productdto = modelMapper().map(pEntity, Productdto.class);
//		System.out.println(productdto.toString());
		
		// Cái này hay
		ProductEntity.class.getDeclaredFields()[0].setAccessible(true);
		System.out.println(ProductEntity.class.getDeclaredFields()[0].getType());
		System.out.println(ProductEntity.class.getDeclaredFields()[0].getName());
		// Vào dto tạo hàm set(name,value) name thì dùng ProductEntity.class.getDeclaredFields()[0].getName() để check
	}
	
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductEntity {
	private int id;
	private String name;
	private float price;
	private List<OrderDetailEntity> orderDetail;
	
//	public String toString() {
//		return "id:"+ this.id + "-\n name:" + this.name + "-\n price:" + this.price
//				+ "\n" + orderDetail.toString();
//	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderDetailEntity {
	private int id;
	private int count;
	private ProductEntity productEntity;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Productdto {
	private int id;
	private String name;
	private float price;
	private List<OrderDetaildto> orderDetail;
	
//	public String toString() {
//		return "id:"+ this.id + "-\n name:" + this.name + "-\n price:" + this.price
//				+ "\n" + orderDetail.toString();
//	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderDetaildto {
	private int id;
	private int count;
	private Productdto productEntity;
}

////////------------- Phía dưới này rất hay nhé
class GFG {
	 
    public static void main(String[] args)
        throws Exception
    {
 
        // create user object
        Employee emp = new Employee();
 
        // print value of uniqueNo
        System.out.println(
            "Value of uniqueNo before "
            + "applying set is "
            + emp.uniqueNo);
 
        // Get the field object
        // M lấy các trường Select * (các cột cần lấy set vào đây)
        Field field
            = Employee.class
                  .getField("uniqueNo");
 
        // Apply set Method
        field.set(emp, (short)1213);
 
        // print value of uniqueNo
        System.out.println(
            "Value of uniqueNo after "
            + "applying set is "
            + emp.uniqueNo);
 
        // print value of salary
        System.out.println(
            "Value of salary before "
            + "applying set is "
            + emp.salary);
 
        // Get the field object
        field = Employee.class.getField("salary");
 
        // Apply set Method
        field.set(emp, 324344.2323);
 
        // print value of salary
        System.out.println(
            "Value of salary after "
            + "applying set is "
            + emp.salary);
    }
}
 
// sample class
class Employee {
 
    // static values
    public static short uniqueNo = 239;
    public static double salary = 121324.13333;
}
