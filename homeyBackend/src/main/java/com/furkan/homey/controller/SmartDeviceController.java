package com.furkan.homey.controller;

import com.furkan.homey.model.dto.SmartDeviceDto;
import com.furkan.homey.service.SmartDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SmartDeviceController extends BaseController{

    private final SmartDeviceService smartDeviceService;


    @PostMapping(SMART_DEVICE)
    public String createSmartDevice(@RequestBody SmartDeviceDto smartDeviceDto) {

        return smartDeviceService.createSmartDevice(smartDeviceDto);
    }

}
