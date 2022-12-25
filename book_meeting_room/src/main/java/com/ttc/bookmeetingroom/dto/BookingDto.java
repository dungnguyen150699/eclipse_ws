package com.ttc.bookmeetingroom.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ttc.bookmeetingroom.common.mapper.CommonMapper;
import com.ttc.bookmeetingroom.model.Employee;
import com.ttc.bookmeetingroom.model.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor 
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookingDto {
	private Integer id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp checkIn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp checkOut;
	private String title;
	private Integer participants;
	private String description;
	private Employee employee;
	private Room room;
	
	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
////		 	Object o1[] = {1,timestamp,null,"nothing",null,null,null,null};
//		Object o1 = new BookingDto(1, timestamp, null, "nothing", null, null, null, null);
//		System.out.println(o1.toString());
////	    	Object o[][] = {{1,timestamp,null,"nothing",null,null,null,null},{1,timestamp,null,"nothing",null,null,null,null}};
//		BookingDto boDto1 = (BookingDto) o1;
//		System.out.println(boDto1);

		Instant instant = timestamp.toInstant();
		System.out.println(instant);
//			BookingDto boDto = CommonMapper.map(o,BookingDto.class);
//			System.out.println(boDto.toString());
	}
	
}