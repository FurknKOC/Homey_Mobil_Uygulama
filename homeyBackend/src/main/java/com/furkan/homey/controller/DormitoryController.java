package com.furkan.homey.controller;

import com.furkan.homey.model.dto.DormitoryDto;
import com.furkan.homey.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DormitoryController extends BaseController{

    private final DormitoryService dormitoryService;

    @PostMapping(CREATE_DORMITORY)
    public String createDormitory(DormitoryDto dormitoryDto) {

        return dormitoryService.createDormitory(dormitoryDto);
    }

}
