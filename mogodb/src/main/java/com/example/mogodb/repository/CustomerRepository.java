package com.example.mogodb.repository;

import java.util.List;

import com.example.mogodb.pojo.dto.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

  Customer findByFirstName(String firstName);
  List<Customer> findByLastName(String lastName);

}