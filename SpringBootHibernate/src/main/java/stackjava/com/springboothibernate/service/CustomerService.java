package stackjava.com.springboothibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.springboothibernate.dao.CustomerDAO;
import stackjava.com.springboothibernate.entities.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	public Customer findById(final int id) {
		return customerDAO.findById(id);
	}

	public void save(final Customer customer) {
		customerDAO.save(customer);
	}

	public void update(final Customer customer) {
		customerDAO.update(customer);
	}

	public void delete(final int id) {
		Customer customer = customerDAO.findById(id);
		if (customer != null) {
			customerDAO.delete(customer);
		}
	}
}
