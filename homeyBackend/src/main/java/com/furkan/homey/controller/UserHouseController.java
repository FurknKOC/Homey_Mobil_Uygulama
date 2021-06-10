package com.furkan.homey.controller;

import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.dto.UserHouseDto;
import com.furkan.homey.service.UserHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserHouseController extends BaseController{

    private final UserHouseService userHouseService;

    @PostMapping(REGISTER_USER_HOUSE)
    public String createUser(@RequestBody UserHouseDto userHouseDto) {

        return userHouseService.registerHouseUser(userHouseDto);
    }
}
