package com.furkan.homey.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.furkan.homey.model.type.ReservationTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ReservationDto extends BaseDto{

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    private Integer status;

    private ReservationTime reservationTime;

    private UserDto userDto;

    private ToolDto toolDto;
}
