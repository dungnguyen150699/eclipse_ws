package Springapi_withException.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Springapi_withException.dto.user;

public interface userService {
	Page<user> findAll(Pageable pageable);
	user findById(int id);
}
