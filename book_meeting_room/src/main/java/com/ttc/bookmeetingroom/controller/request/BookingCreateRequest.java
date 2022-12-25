package com.ttc.bookmeetingroom.controller.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ttc.bookmeetingroom.model.Employee;
import com.ttc.bookmeetingroom.model.Room;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookingCreateRequest {
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
	
}
