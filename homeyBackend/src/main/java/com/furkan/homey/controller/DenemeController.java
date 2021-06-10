package com.furkan.homey.controller;

import com.furkan.homey.model.dto.MailDto;
import com.furkan.homey.service.DenemeService;
import com.furkan.homey.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.furkan.homey.controller.BaseController.SEND_MAIL;

@RestController
@RequiredArgsConstructor
public class DenemeController {

    private final DenemeService denemeService;
    private final MailService mailService;

    @GetMapping("dene")
    public String deneme(){
        return denemeService.dene();
    }

    @PostMapping(SEND_MAIL)
    public void sendMail(@RequestBody MailDto mailDto) {

        mailService.sendSimpleMessage(mailDto);
    }
}
