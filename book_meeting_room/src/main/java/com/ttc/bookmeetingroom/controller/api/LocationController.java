package com.ttc.bookmeetingroom.controller.api;

import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.request.LocationCreateRequest;
import com.ttc.bookmeetingroom.controller.request.LocationUpdateRequest;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import com.ttc.bookmeetingroom.dto.LocationDto;
import com.ttc.bookmeetingroom.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("location")
@CrossOrigin(origins= {"*"})
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/get-by-conditions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<LocationDto>> getAllLocations(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "name", required = false) String name,
            Pageable pageable) {

        Page<LocationDto> page = locationService.getAllByCondition(id, name , pageable);

        ResponseBodyDto<LocationDto> res = new ResponseBodyDto<>(pageable, page, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<LocationDto>> createLocation(
            @RequestBody LocationCreateRequest request) {
        LocationDto locationDto = locationService.create(request);
        ResponseBodyDto<LocationDto> res = new ResponseBodyDto<>(locationDto,
                ResponseCodeEnum.R_201, "CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<LocationDto>> updateLocation(
            @PathVariable(name = "id") Integer id,
            @RequestBody LocationUpdateRequest request) {
        LocationDto locationDto = locationService.update(request, id);
        ResponseBodyDto<LocationDto> res = new ResponseBodyDto<>(locationDto, ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<LocationDto>> deleteLocation(
            @PathVariable(name = "id") Integer id) {
        locationService.delete(id);
        ResponseBodyDto<LocationDto> res = new ResponseBodyDto<>(ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
