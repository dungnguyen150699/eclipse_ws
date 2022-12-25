package com.ttc.bookmeetingroom.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ttc.bookmeetingroom.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoomUpdateRequest {

    private String name;

    private Location location;

    private Integer capacity;

    private String description;

    private String image;

}
