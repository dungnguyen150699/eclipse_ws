package com.ttc.bookmeetingroom.service;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ttc.bookmeetingroom.controller.request.BookingCreateRequest;
import com.ttc.bookmeetingroom.controller.request.BookingUpdateRequest;
import com.ttc.bookmeetingroom.dto.BookingDto;


public interface BookingService{
	BookingDto create(BookingCreateRequest request);
	
	void detele(Integer id);
	
	BookingDto findbyID(Integer id);
	
	BookingDto update(BookingUpdateRequest request , Integer id);
	
	Page<BookingDto> findAll(Pageable pageable);
	
	Page<BookingDto> getAllByCondition(Integer id,Timestamp check_in,Timestamp check_out,String description,Integer participants
    		,String title,Integer employee_id,Integer room_id,Pageable pageable);
}
