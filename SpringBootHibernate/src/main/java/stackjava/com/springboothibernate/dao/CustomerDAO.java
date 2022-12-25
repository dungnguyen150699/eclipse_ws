package stackjava.com.springboothibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.springboothibernate.entities.Customer;


@Repository(value = "customerDAO")
@Transactional(rollbackFor = Exception.class)
public class CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(final Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(customer);
	}

	public void update(final Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer);
	}

	public Customer findById(final int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	public void delete(final Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.remove(customer);
	}

	public List<Customer> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("FROM Customer", Customer.class).getResultList();
	}
}
