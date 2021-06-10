package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHouseDto extends BaseDto{

    private String uuid;

    private Integer houseNumber;
}
