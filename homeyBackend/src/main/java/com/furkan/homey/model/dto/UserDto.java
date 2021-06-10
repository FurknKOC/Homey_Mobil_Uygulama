package com.furkan.homey.model.dto;

import com.furkan.homey.model.type.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto{

    private String name;

    private String surname;

    private String username;

    private String password;

    private String about;

    private Role role;

    private String email;

    private String phone;

    private ContactDto contactDto;

    private UserHouseDto userHouseDto;

    private UserRoomDto userRoomDto;

    private List<ApartDto> apartDtos = new ArrayList<>();

    private List<DormitoryDto> dormitoryDtos = new ArrayList<>();

    private List<ReservationDto> reservationDtos = new ArrayList<>();

    private List<ComplaintDto> complaintDtos = new ArrayList<>();

}
