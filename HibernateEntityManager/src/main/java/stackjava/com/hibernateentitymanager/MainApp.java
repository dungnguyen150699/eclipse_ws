package stackjava.com.hibernateentitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import stackjava.com.hibernateentitymanager.entities.Customer;

public class MainApp {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Customer customer = new Customer("Kai", "Viet Nam");
		
		// save - persist
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		
		// update
		entityManager.getTransaction().begin();				
		customer.setName("Sena");
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
		
		//remove
		entityManager.getTransaction().begin();				
		entityManager.remove(customer);
		entityManager.getTransaction().commit();		
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
