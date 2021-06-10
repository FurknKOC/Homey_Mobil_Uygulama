package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto extends BaseDto{

    private DormitoryDto dormitoryDto;

    private UserRoomDto userRoomDto;
}
