package com.ttc.bookmeetingroom.controller.api;

import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.request.RoomCreateRequest;
import com.ttc.bookmeetingroom.controller.request.RoomUpdateRequest;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import com.ttc.bookmeetingroom.dto.RoomDto;
import com.ttc.bookmeetingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("room")
@CrossOrigin(origins= {"*"})
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/get-by-conditions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<RoomDto>> getByConditions(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "location_id", required = false) Integer locationId,
            @RequestParam(name = "capacity_from", required = false) Integer capacityFrom,
            @RequestParam(name = "capacity_to", required = false) Integer capacityTo,
            @RequestParam(name = "description", required = false) String description,
            Pageable pageable) {

        Page<RoomDto> page = roomService.getAllByCondition(id, name, locationId, capacityFrom, capacityTo, description, pageable);

        ResponseBodyDto<RoomDto> res = new ResponseBodyDto<>(pageable, page, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<RoomDto>> createRoom(
            @RequestBody RoomCreateRequest request) {
        RoomDto roomDto = roomService.create(request);
        ResponseBodyDto<RoomDto> res = new ResponseBodyDto<>(roomDto,
                ResponseCodeEnum.R_201, "CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<RoomDto>> updateRoom(
            @PathVariable(name = "id") Integer id,
            @RequestBody RoomUpdateRequest request) {
        RoomDto roomDto = roomService.update(request, id);
        ResponseBodyDto<RoomDto> res = new ResponseBodyDto<>(roomDto, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<RoomDto>> deleteRoom(
            @PathVariable(name = "id") Integer id) {
        roomService.delete(id);
        ResponseBodyDto<RoomDto> res = new ResponseBodyDto<>(ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
