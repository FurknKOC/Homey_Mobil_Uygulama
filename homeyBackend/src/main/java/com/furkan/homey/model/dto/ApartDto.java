package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApartDto extends BaseDto{

    private Integer status;

    private String address;

    private String name;

    private Integer houseCount;

    private UserDto userDto;

    private List<HouseDto> houseDtos = new ArrayList<>();

    private List<ToolDto> toolDtos = new ArrayList<>();
}
