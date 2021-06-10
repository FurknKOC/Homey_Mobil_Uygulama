package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseDto extends BaseDto{

    private ApartDto apartDto;

    private UserHouseDto userHouseDto;
}
