package com.java8.springboot.java.javareflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.thymeleaf.spring5.util.FieldUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class JavaReflection {
	
	public void setDataToModel() {
		
	}
	
	public static void main(String[] args) {
//		ReflectionConfig reConfig = new ReflectionConfig(ModelReflection.class);
//		reConfig.testGetFieldName();
	}

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class ModelReflection{
	// if primitive can reflect so wrapper can too;
	public int intValue;
	private long longValue; // Same Double,Decimal=BigInt
	private String stringValue;
	private byte byteValue;
	private char charValue;
	private short shortValue;
	
	public String toString() {
		return this.stringValue;
	}
	// i see it almost type primitye so let start!.
}

@AllArgsConstructor
@Data
@NoArgsConstructor
@Slf4j
class ReflectionConfig{
	private Class clazz;
	
	public String[] testGetFieldName() {
		Field []fields = this.clazz.getFields(); // Only get all field have access modify is PUBLIC
		System.out.println("Length Size arrays Field " + fields.length);
		// Cho phép truy cập public, private , protect sẽ được vô hiệu hóa khi cờ = true; 
		List<Field> listField = Arrays.asList(fields);
		System.out.println(listField.toString());
		listField.stream().forEach(f ->{
			f.setAccessible(true);
			System.out.println(f.getName());
		});	
		return null;
	}
	
	public void getAllField() {
		Field []fields = this.clazz.getDeclaredFields();
		System.out.println(fields.length);
	}
	
	public void getFieldNotPublicAccess() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		clazz.getSuperclass() ;// Find Class parent
		Field field = this.clazz.getDeclaredField("longValue"); // Can get Field althrought it is private access;
		// With setAccessible() you change the behavior of the AccessibleObject, i.e.
		// the Field instance, but not the actual field of the class
		field.setAccessible(true); // After you get field by name should set Access it is Public by this way.
		System.out.println(field.getName() + "-----" + field.getModifiers());
		System.out.println("after set access = true number public still = 1 : " + this.clazz.getFields().length);
	}
	
	public void getFieldValue_FromObjectClassHaveValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// field.get(object) // Get Field Value from object object is class hava same Field Name
		ModelReflection model = new ModelReflection();
		model.setLongValue(3l);
		Field field = this.clazz.getDeclaredField("longValue");
		field.setAccessible(true); // if not set --> throw IllegalAccessException
		System.out.println(field.get(model) + "--------" + field.getType().getName()); // Kiểu dữ liệu trả về là 1 class(StringClass, IntClass,..) 
	}
	
	public void setValue_ToObject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		ModelReflection model = new ModelReflection();
		model.setLongValue(3l); // this have 3l
		// after 
		Field field = this.clazz.getDeclaredField("longValue");
		field.setAccessible(true); // Still need set Accessible Modify = true =))
		field.set(model, 4l);
		System.out.println(model.getLongValue());
	}
	
	public static <T>void  trimValues(T object) {
		Field []fields = object.getClass().getDeclaredFields();
		for(Field field : fields) {
			try {
				if(field.getType().getName().equals(String.class.getName())) {
					field.setAccessible(true);
					String value = field.get(object).toString();
					if(value != null || !value.isEmpty()) {
						field.set(object, value.trim());
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
			}
		}
	}
	
	
	public static <T> void  safeObjectNullValue(T object) {
		Field []fields = object.getClass().getDeclaredFields();
		for(Field field : fields) {
			try {
				if(field.getType().getName().equals(String.class.getName())) {
					field.setAccessible(true);
//					String value = field.get(object).toString();
					String value = field.get(object) == null ? "" : field.get(object).toString();
					if(value == null) {
						field.set(object, "");
					}
					field.set(object, value);
				}
			}catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
			}
		}
	}
	
	
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ReflectionConfig reConfig = new ReflectionConfig(ModelReflection.class);
//		reConfig.testGetFieldName();
//		reConfig.getFieldNotPublicAccess();
//		reConfig.getFieldValue_FromObjectClassHaveValue();
//		reConfig.setValue_ToObject();
//		reConfig.getAllField();
		
		ModelReflection modelReflection = new ModelReflection();
		modelReflection.setStringValue(null);
//		modelReflection.setIntValue(1234);
//		reConfig.trimValues(modelReflection);
		reConfig.safeObjectNullValue(modelReflection);
		System.out.println(modelReflection.getStringValue());
		
		// okey done java Reflect
		
	}
}
