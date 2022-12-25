package com.java8.springboot.spring.Common.Annotation;

// Sẽ tạo 1 Bean nếu

//https://viblo.asia/p/spring-boot-huong-dan-tao-bean-co-dieu-kien-voi-conditional-gDVK2270KLj
//@Config
//@ConditionalOnBean(RandomBean.class) // Nếu 1 Bean đã có
//@ConditionalOnProperty(
//        value="loda.bean2.enabled",
//        havingValue = "true", // Nếu giá trị loda.bean2.enabled  = true thì Bean mới được khởi tạo
//        matchIfMissing = false) // matchIFMissing là giá trị mặc định nếu không tìm thấy property loda.bean2.enabled // Nếu Thỏa Property
//@ConditionalOnExpression(
//        "${loda.expression1.enabled:true} and ${loda.expression2.enabled:true}"
//) // Muốn thỏa mãn nhiều điều kiện
public class Annotation_Conditional {

}
