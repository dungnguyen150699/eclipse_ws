package com.ttc.bookmeetingroom.service.impl;

import com.ttc.bookmeetingroom.common.error.NotFoundException;
import com.ttc.bookmeetingroom.common.mapper.CommonMapper;
import com.ttc.bookmeetingroom.controller.request.DepartmentCreateRequest;
import com.ttc.bookmeetingroom.controller.request.DepartmentUpdateRequest;
import com.ttc.bookmeetingroom.dto.DepartmentDto;
import com.ttc.bookmeetingroom.model.Department;
import com.ttc.bookmeetingroom.repository.DepartmentRepository;
import com.ttc.bookmeetingroom.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Page<DepartmentDto> getAllByCondition(Integer id, String name, Pageable pageable) {
       Page<Department> departmentPage = departmentRepository.getAllByCondition(id,  name, pageable);
       return CommonMapper.toPage(departmentPage,DepartmentDto.class,pageable);

    }

    @Override
    public DepartmentDto create(DepartmentCreateRequest request) {
        Department department = CommonMapper.map(request,Department.class);
        return CommonMapper.map(departmentRepository.save(department),DepartmentDto.class);
    }

    @Override
    public DepartmentDto update(DepartmentUpdateRequest request, Integer id) {
        Department department = departmentRepository.findById(id);
        if(department == null){
            throw new NotFoundException("Not Found Department");
        }CommonMapper.copyPropertiesIgnoreNull(request,department);
        department=departmentRepository.save(department);
        return CommonMapper.map(department,DepartmentDto.class);

    }

    @Override
    public void delete(Integer id) {
        Department department = departmentRepository.findById(id);
        if(department == null){
            throw new NotFoundException("Not Found Department");
        }
        departmentRepository.deleteById(id);
    }
}
