package com.ttc.bookmeetingroom.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.ttc.bookmeetingroom.common.error.NotFoundException;
import com.ttc.bookmeetingroom.common.mapper.CommonMapper;
import com.ttc.bookmeetingroom.controller.request.BookingCreateRequest;
import com.ttc.bookmeetingroom.controller.request.BookingUpdateRequest;
import com.ttc.bookmeetingroom.dto.BookingDto;
import com.ttc.bookmeetingroom.model.Booking;
import com.ttc.bookmeetingroom.repository.BookingRepository;
import com.ttc.bookmeetingroom.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public BookingDto create(BookingCreateRequest request) {
		// TODO Auto-generated method stub
		Booking b = CommonMapper.map(request,Booking.class);
		return CommonMapper.map(bookingRepository.save(b),BookingDto.class);
	}

	@Override
	public void detele(Integer id) {
		// TODO Auto-generated method stub
		Booking b = bookingRepository.findById(id);
		if(b == null) throw new NotFoundException("Not found booking");
		bookingRepository.deleteById(id);
	}

	@Override
	public BookingDto findbyID(Integer id) {
		return CommonMapper.map(bookingRepository.findById(id),BookingDto.class);
	}

	@Override
	public BookingDto update(BookingUpdateRequest request, Integer id) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(id);
		if(booking == null) throw new NotFoundException("Not found booking");
		CommonMapper.copyPropertiesIgnoreNull(request, booking);
		booking = bookingRepository.save(booking);
		return CommonMapper.map(booking, BookingDto.class);
	}

	@Override
	public Page<BookingDto> getAllByCondition(Integer id, Timestamp check_in, Timestamp check_out, String description,
			Integer participants, String title, Integer employee_id, Integer room_id, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Booking> bookingPage = bookingRepository.getAllByCondition(id, check_in, check_out, description, participants, title, employee_id, room_id, pageable);
		return CommonMapper.toPage(bookingPage, BookingDto.class, pageable);
	}

	@Override
	public Page<BookingDto> findAll(Pageable pageable) {
		Page<Booking> bookingPage = bookingRepository.findAll(pageable);
		return CommonMapper.toPage(bookingPage, BookingDto.class, pageable);
	}

}
