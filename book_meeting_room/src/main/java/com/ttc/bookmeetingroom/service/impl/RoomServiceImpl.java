package com.ttc.bookmeetingroom.service.impl;

import com.ttc.bookmeetingroom.common.mapper.CommonMapper;
import com.ttc.bookmeetingroom.common.error.NotFoundException;
import com.ttc.bookmeetingroom.controller.request.RoomCreateRequest;
import com.ttc.bookmeetingroom.controller.request.RoomUpdateRequest;
import com.ttc.bookmeetingroom.dto.RoomDto;
import com.ttc.bookmeetingroom.model.Room;
import com.ttc.bookmeetingroom.repository.RoomRepository;
import com.ttc.bookmeetingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Page<RoomDto> getAllByCondition(Integer id, String name, Integer locationId, Integer capacityFrom, Integer capacityTo, String description, Pageable pageable) {
        Page<Room> roomPage = roomRepository.getAllByCondition(id, name, locationId, capacityFrom, capacityTo, description, pageable);
        return CommonMapper.toPage(roomPage, RoomDto.class, pageable);
    }

    @Override
    public RoomDto create(RoomCreateRequest request) {
        Room room = CommonMapper.map(request, Room.class);
        return CommonMapper.map(roomRepository.save(room), RoomDto.class);
    }

    @Override
    public RoomDto update(RoomUpdateRequest request, Integer id) {
        Room room = roomRepository.findById(id);
        if (room == null) {
            throw new NotFoundException("Not found room");
        }
        CommonMapper.copyPropertiesIgnoreNull(request, room);
        room = roomRepository.save(room);
        return CommonMapper.map(room, RoomDto.class);
    }

    @Override
    public void delete(Integer id) {
        Room room = roomRepository.findById(id);
        if (room == null) {
            throw new NotFoundException("Not found room");
        }
        roomRepository.deleteById(id);
    }
}
