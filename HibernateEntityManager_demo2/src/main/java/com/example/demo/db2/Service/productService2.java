package com.example.demo.db2.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.annotations.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.db2.Entity.Product2;
import com.example.demo.db2.jpa.ProductRepository2;



@Service
public class productService2 {
//	Có thể gọi EntityManager bằng cách này nhưng chỉ select thôi vì cái này nó là của hệ thống
//	@PersistenceContext
//	EntityManager entityManager;
//	Hoặc gọi bằng cách này // Cách này bạn phải có file Persistence.xml

//  @Autowired
//  private ProductRepository2 pr;
//  @Autowired
//  @Qualifier(value = "masterEntityManager2")
//  private EntityManagerFactory emf;
    private EntityManagerFactory emf = null;

  
  public EntityManager getEntityManager() {

        if (emf == null || !emf.isOpen()) {
        	// Đưa map porperties thì đc vì cái poperties nó ko ăn
        	 Map<String, String> persistenceMap = new HashMap<>();
           persistenceMap.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
           persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/jpaemvshibernate");
           persistenceMap.put("javax.persistence.jdbc.user", "root");
           persistenceMap.put("javax.persistence.jdbc.password", "b17dccn160");
//           // Xem cả cái này là gì nữa
////           persistenceMap.put("hibernate.ejb.event.pre-update", "com.viettel.scontract.entities.evt.ReplicationUpdateEventListener");
           persistenceMap.put("hibernate.hikari.minimumIdle", "5");
           persistenceMap.put("hibernate.hikari.maximumPoolSize", "100");
           persistenceMap.put("hibernate.hikari.idleTimeout", "30000");
           persistenceMap.put("hibernate.hikari.connectionTimeout", "300000000");
           // Xem cái dưới này là gì
//           persistenceMap.put("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
//           emf = Persistence.createEntityManagerFactory("testxx",persistenceMap);
            emf = Persistence.createEntityManagerFactory("abc");
        }
        System.out.println(emf);
        return emf.createEntityManager();

    }

   public void closeEntityManager() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        emf = null;
    }
	
	
//	insert hoặc update delete cần cấu hình entityManager
	public void saveProduct() {
		EntityManager entityManager = getEntityManager();
        Product2 product = new Product2();
		product.setName("name1"); product.setDescription("D1");
        product.setId(1000);
//
		entityManager.getTransaction().begin();
//		entityManager.persist(product);
        ProductRepository2 pr = new JpaRepositoryFactory(entityManager).getRepository(ProductRepository2.class);
		pr.save(product);
		entityManager.getTransaction().commit();
        entityManager.close();
//		pr.save(product);
	}
	
//	public Product2 findById(int idProduct) {
//		EntityManager em = getEntityManager();
//		Product2 p = em.find(Product.class, idProduct);
//		em.close();
//		return p;
//	}
	
	public List<Product2> getAll(){
		EntityManager em = getEntityManager();
		List<Product2> list = em.createNativeQuery("Select * from Product2",Product2.class).getResultList();
//		return pr.findAll();
		return list;
	}
	
	
}
