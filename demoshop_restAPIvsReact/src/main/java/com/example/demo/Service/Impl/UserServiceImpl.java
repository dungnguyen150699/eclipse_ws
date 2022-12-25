package com.example.demo.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.OrderEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.example.demo.error.DoubleUsernameException;
import com.example.demo.error.NotFoundException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	@Autowired
	private UserRepository ur;
	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		ur.deleteById(id);
	}

	@Override
	public UserEntity create(UserEntity user) {
		// TODO Auto-generated method stub
		List<UserEntity> users = ur.loadUserByUsername(user.getUsername());
		if(users.size() > 0) throw new DoubleUsernameException("UserName này đã có người sử dụng!. Vui lòng thừ lại UserName khác.");
		return ur.save(user);
	}

	@Override
	public Page<UserEntity> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return ur.findAll(pageable);
	}

	@Override
	public Page findOrderByUserId(int id, Pageable pageable) {
		// TODO Auto-generated method stub
		UserEntity user = findUserByID(id);
		return new PageImpl<OrderEntity>(user.getOrders(),pageable,user.getOrders().size());
	}

	@Override
	public UserEntity findUserByID(int id) {
		// TODO Auto-generated method stub
		UserEntity user = ur.findById(id);
		if(user == null) throw new NotFoundException("Not found User");
		return user;
	}

	@Override
	public UserEntity checkingLogin(UserEntity user) {
		// TODO Auto-generated method stub
		UserEntity u = null;
		u = ur.checkingLogin(user.getUsername(), user.getPassword());
		if(u == null) throw new NotFoundException("Not found User");
		return u;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<UserEntity> users= ur.loadUserByUsername(username);
//		System.out.println(user.getUsername()+"______"+user.getPassword());
		System.out.println("tim user");
		if(users.size() == 0) {
			System.out.println("null user");
			throw new UsernameNotFoundException("Khong tim thay user!");
//			return null;
		}
		return new UserDetailImpl(users.get(0));
	}

	@Override
	public UserEntity save(UserEntity user) {
		// TODO Auto-generated method stub
		if(ur.loadUserByUsername(user.getUsername()).size() > 0) {
			throw new DoubleUsernameException("Username bị trùng! vui lòng nhập Username khác");
		}
		return ur.save(user);
	}

}
