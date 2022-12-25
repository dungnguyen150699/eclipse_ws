package com.ttc.bookmeetingroom.controller.api;

import java.sql.Timestamp;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.request.BookingCreateRequest;
import com.ttc.bookmeetingroom.controller.request.BookingUpdateRequest;
import com.ttc.bookmeetingroom.controller.request.RoomCreateRequest;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import com.ttc.bookmeetingroom.dto.BookingDto;
import com.ttc.bookmeetingroom.dto.RoomDto;
import com.ttc.bookmeetingroom.service.BookingService;

@Controller
@RequestMapping("booking")
@CrossOrigin(origins= {"*"})
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<BookingDto>> createBooking(
            @RequestBody BookingCreateRequest request) {
		System.out.println(request.getTitle());
        BookingDto bookingDto = bookingService.create(request);
        ResponseBodyDto<BookingDto> res = new ResponseBodyDto<>(bookingDto,
                ResponseCodeEnum.R_201, "CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
	
	@DeleteMapping(value= "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBodyDto<BookingDto>> deleteBooking(@PathVariable (name="id") Integer id){
		bookingService.detele(id);
		ResponseBodyDto<BookingDto> res = new ResponseBodyDto<BookingDto>(ResponseCodeEnum.R_200, "OK");
		ResponseEntity re = new ResponseEntity(res,HttpStatus.OK);
		return re;
	}
	
	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBodyDto<BookingDto>> updateBooking(
			@PathVariable(name="id") Integer id,
			@RequestBody BookingUpdateRequest request){
		BookingDto bookingDto = bookingService.update(request, id);
		ResponseBodyDto<BookingDto> res = new ResponseBodyDto<BookingDto>(bookingDto,ResponseCodeEnum.R_200, "OK");
		return new ResponseEntity(res,HttpStatus.OK);
	}
	
	@GetMapping(value = "/showBookingAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBodyDto <BookingDto>> showBookingAll(Pageable pageable){
		Page<BookingDto> page = bookingService.findAll(pageable);
		ResponseBodyDto<BookingDto> res = new ResponseBodyDto<>(pageable, page, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping(value = "/get-by-conditions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBodyDto<BookingDto>> getAllCompanies(
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "check_in", required = false) Timestamp check_in,
			@RequestParam(name = "check_out", required = false) Timestamp check_out,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "participants", required = false) Integer participants,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "employee_id", required = false) Integer employee_id,
			@RequestParam(name = "room_id", required = false) Integer room_id,
			Pageable pageable){
		
			Page<BookingDto> page = bookingService.getAllByCondition(id, check_in, check_out, description, participants, title, employee_id, room_id, pageable);
			ResponseBodyDto<BookingDto> res = new ResponseBodyDto<>(pageable, page, ResponseCodeEnum.R_200, "OK");
	        return ResponseEntity.status(HttpStatus.OK).body(res);
		}
}
