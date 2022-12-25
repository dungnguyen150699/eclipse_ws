package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//	UserDTO
	@Query( value = "Select * from users where username = :username and password = :password", nativeQuery=true )
	public UserEntity checkingLogin(@Param(value = "username") String userName, @Param(value = "password") String password);
	
	public UserEntity findById(int id);
		
	@Query( value = "Delete from users where id = :id", nativeQuery=true )
	public void deleteById(@Param(value = "id") int id);
	
	@Query( value = "Select * from users where username = :username", nativeQuery=true )
	public List<UserEntity> loadUserByUsername(@Param(value="username") String username);
}
