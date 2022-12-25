package com.ttc.bookmeetingroom.service;

import com.ttc.bookmeetingroom.controller.request.RoomCreateRequest;
import com.ttc.bookmeetingroom.controller.request.RoomUpdateRequest;
import com.ttc.bookmeetingroom.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    Page<RoomDto> getAllByCondition(Integer id, String name, Integer locationId, Integer capacityFrom,
                                    Integer capacityTo, String description, Pageable pageable);

    RoomDto create(RoomCreateRequest request);

    RoomDto update(RoomUpdateRequest request, Integer id);

    void delete(Integer id);
}
