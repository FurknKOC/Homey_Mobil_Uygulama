package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto extends BaseDto{

    private String email;

    private String phone;

    private String twitter;

    private String instagram;

    private UserDto userDto;

}
