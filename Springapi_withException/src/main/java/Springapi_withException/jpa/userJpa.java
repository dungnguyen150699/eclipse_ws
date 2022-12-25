package Springapi_withException.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Springapi_withException.Entity.userEntity;
import Springapi_withException.dto.user;

@Repository
public interface userJpa extends JpaRepository<userEntity,Integer> {
	
	Page<userEntity> findAll(Pageable pageable);
	
	userEntity findById(int id);
}
