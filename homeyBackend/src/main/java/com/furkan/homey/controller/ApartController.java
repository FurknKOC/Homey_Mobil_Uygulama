package com.furkan.homey.controller;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.service.ApartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApartController extends BaseController{

    private final ApartService apartService;

    @PostMapping(CREATE_APART)
    public String createApart(@RequestBody ApartDto apartDto){

        return apartService.createApart(apartDto);
    }

    @PostMapping(GET_ALL_APART)
    public List<ApartDto> getAllApartByUser(@RequestBody UserDto userDto) {

        return apartService.getAllApartByUser(userDto);
    }

}
