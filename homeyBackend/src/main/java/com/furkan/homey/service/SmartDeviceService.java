package com.furkan.homey.service;

import com.furkan.homey.model.dto.SmartDeviceDto;
import com.furkan.homey.model.entity.SmartDevice;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.type.Status;
import com.furkan.homey.repository.SmartDeviceRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmartDeviceService extends BaseService{

    private final SmartDeviceRepository smartDeviceRepository;
    private final UserRepository userRepository;

    public String createSmartDevice(SmartDeviceDto smartDeviceDto) {

        User user = userRepository.findByUsername(getLoginUserName());

        List<SmartDevice> smartDeviceControl = smartDeviceRepository.getAllByStatusAndUserId(Status.ACTIVE,user.getId());

        if (smartDeviceControl.isEmpty()) {
            SmartDevice smartDevice = new SmartDevice();

            smartDevice.setFcmToken(smartDeviceDto.getFcmToken());
            smartDevice.setUserId(user.getId());

            smartDeviceRepository.save(smartDevice);

        } else {
            smartDeviceControl.get(0).setFcmToken(smartDeviceDto.getFcmToken());
            smartDeviceRepository.saveAll(smartDeviceControl);
        }

        return SUCCESS_RESPONSE;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void newTxInactiveFcmToken(String token) {
        smartDeviceRepository.findByFcmToken(token)
                .ifPresent(smartDevice -> {
                    smartDevice.setInactive();
                    smartDeviceRepository.save(smartDevice);
                });
    }
}
