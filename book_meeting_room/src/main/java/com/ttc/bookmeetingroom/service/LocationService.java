package com.ttc.bookmeetingroom.service;

import com.ttc.bookmeetingroom.controller.request.LocationCreateRequest;
import com.ttc.bookmeetingroom.controller.request.LocationUpdateRequest;

import com.ttc.bookmeetingroom.dto.LocationDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {

    Page<LocationDto> getAllByCondition(Integer id, String name, Pageable pageable);

    LocationDto create(LocationCreateRequest request);

    LocationDto update(LocationUpdateRequest request, Integer id);

    void delete(Integer id);

}
