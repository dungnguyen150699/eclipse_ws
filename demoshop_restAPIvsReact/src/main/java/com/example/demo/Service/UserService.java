package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;

public interface UserService{	
	
	public UserEntity save(UserEntity user);
	
	public UserEntity checkingLogin(UserEntity user);
	
	public UserEntity findUserByID(int id);
	
	public Page<UserEntity> getAll(Pageable pageable);
	
	public void deleteById(int id);
	
	UserEntity create(UserEntity user);
	
	Page findOrderByUserId(int id,Pageable pageable);
	
	UserDetails loadUserByUsername(String username);
}
