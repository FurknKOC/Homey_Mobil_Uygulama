package com.furkan.homey.service;

import com.furkan.homey.model.dto.MailDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.repository.ApartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.furkan.homey.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MailService extends BaseService{

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final ApartRepository apartRepository;

    public void sendSimpleMessage(MailDto maildto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(maildto.getSubject());
        message.setText(maildto.getContent());
        message.setTo(maildto.getTo());
        message.setFrom(maildto.getFrom());

        javaMailSender.send(message);
    }

    public String sendUuidForApart(MailDto mailDto) {

        User user = userRepository.findByUsername(getLoginUserName());

        SimpleMailMessage message = new SimpleMailMessage();
        Apart apart = apartRepository.getOne(mailDto.getApartId());

        message.setSubject("Apart Kayıt");
        message.setText("Apart'a kayıt olmak için bu kodu kullanınız : " + apart.getUuid());
        message.setTo(mailDto.getTo());
        //message.setFrom(mailDto.getFrom());

        javaMailSender.send(message);

        return SUCCESS_RESPONSE;
    }
}
