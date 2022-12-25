package com.ttc.bookmeetingroom.service;

import com.ttc.bookmeetingroom.controller.request.DepartmentCreateRequest;
import com.ttc.bookmeetingroom.controller.request.DepartmentUpdateRequest;
import com.ttc.bookmeetingroom.controller.request.LocationCreateRequest;
import com.ttc.bookmeetingroom.controller.request.LocationUpdateRequest;
import com.ttc.bookmeetingroom.dto.DepartmentDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Page<DepartmentDto> getAllByCondition(Integer id, String name, Pageable pageable);

    DepartmentDto create(DepartmentCreateRequest request);

    DepartmentDto update(DepartmentUpdateRequest request, Integer id);

    void delete(Integer id);
}
