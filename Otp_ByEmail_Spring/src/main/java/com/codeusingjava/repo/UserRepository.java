package com.codeusingjava.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeusingjava.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);

} 

