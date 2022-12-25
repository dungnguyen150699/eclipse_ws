package com.java8.springboot.spring;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
 
//  https://codelearn.io/sharing/annotation-trong-java-la-gi
//  https://docs.oracle.com/javase/tutorial/java/annotations/index.html

public class AnnotationUserEdit {
	public static void main(String[] args) {
		
	}
}
/*
 * Một Annotation sẽ đượng định nghĩa bởi các Meta-Annotations. 
 * Các Meta-Annotations gồm @Retention, @Target, @Documented, @Inherited.   Cụ thể 
 */
@Retention(value = RetentionPolicy.SOURCE)
//Nó tồn tại trên mã nguồi và trình biên dịch không nhận ra
@Target(value = {ElementType.METHOD, ElementType.FIELD})
//Nó sẽ được dùng chú thích tren mot method
@interface MyCustomAnnotation{    
}

//Sử dụng
class UsingAnno {
    @MyCustomAnnotation  //Gán trước một field
    private int myAge = 22;    
    
    @MyCustomAnnotation   //Gán trước một method
    public void aMethod(){
        
    }
}

//define annotation for method
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyMethodAnnotation {
 int value();
 int value2();
}

//define annotation for class
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyClassAnnotation {
 String classType();
}

//Apply annotation
@MyClassAnnotation(classType = "ENTITY")
class Hello {
 @MyMethodAnnotation(value = 10,value2 = 11)
 public void sayHello() {
     System.out.println("hello!");
 }
}

//Access annotation
class Test {
 public static void main(String args[]) throws Exception {
     // get method
     Method method = Hello.class.getClassLoader()
             .loadClass("com.java8.springboot.spring.Hello")
             .getMethod("sayHello");
     // get annotation from method
     MyMethodAnnotation manno = method
             .getAnnotation(MyMethodAnnotation.class);
     // show value of annotation
     System.out.println("value is: " + manno.value() + "\nvalue2 is:" + manno.value2() );

     // get annotation from class
     MyClassAnnotation canno = Hello.class
             .getAnnotation(MyClassAnnotation.class);
     // show value of annotation
     System.out.println("classType is: " + canno.classType());
 }
}