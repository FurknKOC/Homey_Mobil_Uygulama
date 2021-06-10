package com.furkan.homey.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkan.homey.exception.SmartDeviceNotFoundException;
import com.furkan.homey.model.dto.PushNotificationRequestDto;
import com.furkan.homey.model.dto.PushNotificationResponse;
import com.furkan.homey.model.entity.SmartDevice;
import com.furkan.homey.model.type.Status;
import com.furkan.homey.repository.SmartDeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PushNotificationService extends BaseService{

    private final FcmService fcmService;
    private final SmartDeviceRepository smartDeviceRepository;

    public String sendPushNotification(PushNotificationRequestDto request) {

        List<SmartDevice> smartDevices = smartDeviceRepository.getAllByStatusAndUserId(Status.ACTIVE, request.getAccountId());

        if (CollectionUtils.isEmpty(smartDevices)) {
            throw new SmartDeviceNotFoundException();
        }

        SmartDevice smartDevice  = smartDevices.get(0);
        try {

            String message = request.getMessage();
            if (request.getMessage().length() > 995) {
                message = request.getMessage().substring(0, 995) + "...";
            }

            fcmService.fcmPush(smartDevice.getFcmToken(), request.getTitle(), message);

        } catch (Exception e) {
            log.warn("Push notification error occurred");
        }
        return SUCCESS_RESPONSE;
    }

}
