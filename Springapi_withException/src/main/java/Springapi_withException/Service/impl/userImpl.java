package Springapi_withException.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Springapi_withException.Entity.userEntity;
import Springapi_withException.Service.userService;
import Springapi_withException.common.mapper;
import Springapi_withException.common.Exception.NotFoundException;
import Springapi_withException.dto.user;
import Springapi_withException.jpa.userJpa;

@Service
public class userImpl implements userService{
	@Autowired userJpa uj;
	@Override
	public Page<user> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<userEntity> page = uj.findAll(pageable);
		List<userEntity> list = page.getContent();
		System.out.println(list.size());
		return mapper.toPage(page, user.class, pageable);
	}
	@Override
	public user findById(int id) {
		// TODO Auto-generated method stub
		userEntity ue = uj.findById(id);
		if(ue == null) {
			throw new NotFoundException("Khong tim thay user");
		}
		return mapper.map(ue, user.class);
	}

}
