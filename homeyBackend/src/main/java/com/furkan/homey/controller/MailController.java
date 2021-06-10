package com.furkan.homey.controller;

import com.furkan.homey.model.dto.MailDto;
import com.furkan.homey.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController extends BaseController{

    private final MailService mailService;

    @PostMapping(SEND_UUID_FOR_APART)
    public String sendUuidForApart(@RequestBody MailDto mailDto) {

        return mailService.sendUuidForApart(mailDto);
    }

}
