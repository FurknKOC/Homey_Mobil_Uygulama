package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ToolDto extends BaseDto{

    private String name;

    private String about;

    private ApartDto apartDto;

    private DormitoryDto dormitoryDto;

    private List<ReservationDto> reservationDtos = new ArrayList<>();
}
