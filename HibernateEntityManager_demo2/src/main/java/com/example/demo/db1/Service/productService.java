package com.example.demo.db1.Service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.db1.Entity.Product;
import com.example.demo.db1.jpa.ProductRepository;

@Service
public class productService {
	// Có thể gọi EntityManager bằng cách này nhưng chỉ select thôi vì cái này nó là
	// của hệ thống
	// Tóm lại cái này ko dùng đc trong spring boot nữa nếu bắt buộc thì sài bản
	// spring thấp thôi
//	@PersistenceContext(unitName = "test") 
//	private EntityManager entityManager;
	// Hoặc gọi bằng cách này // Cách này bạn phải có file Persistence.xml

	@Autowired
	@Qualifier(value = "masterEntityManager")
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private ProductRepository pjpa;

	@Autowired
	private ProductRepository pr;

	public EntityManager getEntityManager() {
///		if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
//			entityManagerFactory = Persistence.createEntityManagerFactory("test");
//
//		}
		return entityManagerFactory.createEntityManager();

	}

	public void closeEntityManager() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
		entityManagerFactory = null;

	}

//	insert hoặc update delete cần cấu hình entityManager
	public void saveProduct() {
		EntityManager entityManager = getEntityManager();
//		-------------
		ProductRepository pjpa = new JpaRepositoryFactory(entityManager).getRepository(ProductRepository.class);
		Product product = new Product("Xe may", "hang my");
//		---------------
		entityManager.getTransaction().begin();
		entityManager.persist(product);
//		-------------
		pjpa.save(product);
//		--------------
		entityManager.getTransaction().commit();
		entityManager.close();
//		pr.save(product);
	}

	/*
	 * The query demonstrates the basic steps to create a criteria. EntityManager
	 * instance is used to create a CriteriaBuilder object. CriteriaQuery instance
	 * is used to create a query object. This query object’s attributes will be
	 * modified with the details of the query. CriteriaQuery.from method is called
	 * to set the query root. CriteriaQuery.select is called to set the result list
	 * type. TypedQuery<T> instance is used to prepare a query for execution and
	 * specifying the type of the query result. getResultList method on the
	 * TypedQuery<T> object to execute a query. This query returns a collection of
	 * entities, the result is stored in a List.
	 */

	public List<Product> criterial_Find() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		// Select Where
		Root<Product> root = cq.from(Product.class);
		Predicate authorNamePredicate = cb.equal(root.get("name"), "Xe may");
		Predicate titlePredicate = cb.like(root.get("description"), "%" + "hang " + "%");
		// Select All
		/*
		 * CriteriaQuery<Product> cq1 = cq.select(root); TypedQuery<Object> typedQuery =
		 * em.createQuery(cq1); List<Object> resultlist = typedQuery.getResultList();
		 */
		cq.where(authorNamePredicate, titlePredicate);

		// Select Join
//		Join<Pet, Owner> owner = pet.join(Pet_.owners);
		TypedQuery<Product> query = em.createQuery(cq);
		System.out.println(query.getParameterValue(1).toString() + "------------");
		return query.getResultList();
	}

	public List<Product> EntityManager_NativeQuery() {
		EntityManager em = getEntityManager();
		String queryString = "select * from Product p where id=:id";
		Query query = em.createNativeQuery(queryString, Product.class);
		query.setParameter("id", 11);
		return query.getResultList();
	}

	public Product findById(int idProduct) {
		EntityManager em = getEntityManager();
		Product p = em.find(Product.class, idProduct);
		em.close();
		return p;
	}

	public List<Product> getAll() {
		EntityManager em = getEntityManager();
		List<Product> list = em.createNativeQuery("Select * from Product", Product.class).getResultList();
		em.close();
		return list;
//		return pr.findAll();
	}

}
