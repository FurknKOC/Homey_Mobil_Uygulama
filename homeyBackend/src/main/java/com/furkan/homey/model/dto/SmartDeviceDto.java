package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartDeviceDto extends BaseDto{

    private Long userId;

    private String fcmToken;

}
