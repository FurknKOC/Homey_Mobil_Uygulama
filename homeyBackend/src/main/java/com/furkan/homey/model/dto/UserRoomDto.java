package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoomDto extends BaseDto{

    private UserDto userDto;

    private RoomDto roomDto;

}
