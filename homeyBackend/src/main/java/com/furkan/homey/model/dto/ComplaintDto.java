package com.furkan.homey.model.dto;

import com.furkan.homey.model.type.ComplaintType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintDto extends BaseDto{

    private String complaint;

    private ComplaintType complaintType;

    private String title;

    private Integer houseNumber;

    private UserDto userDto;

    private UserDto userManagerDto;

    private ApartDto apartDto;

    private DormitoryDto dormitoryDto;
}
