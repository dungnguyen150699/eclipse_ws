package stackjava.com.hibernateentitymanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import stackjava.com.hibernateentitymanager.entities.Customer;

public class CustomerDAO {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public void save(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}

	public Customer findById(int id) {
		Customer customer = entityManager.find(Customer.class, id);
		return customer;
	}

	public List<Customer> findAll() {
		return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	}

	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
