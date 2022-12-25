package com.example.demo.hibernate;

import com.example.demo.pojo.Product;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class HibernateSessionTester {
    public static void main(String[] args) {
        Session session = HibernateSessionUtils.getSessionFactory().openSession();
        Query query = session.createQuery("From Product where id=1");//HQL Session
        List<Product> list = query.getResultList();
        list.forEach(x-> System.out.printf("%d -%s -%s\n",x.getId(), x.getName(),x.getDescription()));

//        Product product = new Product();
//        product.setName("Tivi");
//        product.setDescription("Hàng xịn");
//        session.save(product);
//        Product product = session.get(Product.class, 1);
//        product.setDescription("Hàng tàu đấy, đừng mua");
//        session.getTransaction().begin();
//        session.delete(product);
//        session.getTransaction().commit();

        session.close();
    }
}
