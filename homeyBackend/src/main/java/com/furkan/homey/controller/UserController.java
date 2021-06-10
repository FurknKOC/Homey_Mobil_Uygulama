package com.furkan.homey.controller;

import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController{

    private final UserService userService;

    @PostMapping(PUBLIC_CREATE_USER)
    public String createUser(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);
    }

    @GetMapping(USER_INFORMATION)
    public UserDto getUserInformation() {

        return userService.getUserInformation();
    }

}
