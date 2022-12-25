package com.ttc.bookmeetingroom.service.impl;

import com.ttc.bookmeetingroom.common.error.NotFoundException;
import com.ttc.bookmeetingroom.common.mapper.CommonMapper;
import com.ttc.bookmeetingroom.controller.request.LocationCreateRequest;
import com.ttc.bookmeetingroom.controller.request.LocationUpdateRequest;
import com.ttc.bookmeetingroom.dto.LocationDto;
import com.ttc.bookmeetingroom.model.Location;
import com.ttc.bookmeetingroom.repository.LocationRepository;
import com.ttc.bookmeetingroom.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {


    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Page<LocationDto> getAllByCondition(Integer id, String name, Pageable pageable) {
        Page<Location> locationPage =locationRepository.getAllByCondition(id, name, pageable);
        return CommonMapper.toPage(locationPage,LocationDto.class,pageable);

    }

    @Override
    public LocationDto create(LocationCreateRequest request) {
        Location location = CommonMapper.map(request, Location.class);
        return CommonMapper.map(locationRepository.save(location) , LocationDto.class);
    }

    @Override
    public LocationDto update(LocationUpdateRequest request, Integer id) {
        Location location = locationRepository.findById(id);
        if (location == null) {
            throw new NotFoundException("Not found location");
        }
        CommonMapper.copyPropertiesIgnoreNull(request, location);
        location = locationRepository.save(location);
        return CommonMapper.map(location, LocationDto.class);
    }

    @Override
    public void delete(Integer id) {
        Location location = locationRepository.findById(id);
        if(location == null) {
            throw new NotFoundException("Not Found Location");
        }
        locationRepository.deleteById(id);
    }
}
