package com.furkan.homey.repository;

import com.furkan.homey.model.entity.SmartDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmartDeviceRepository extends JpaRepository<SmartDevice, Long> {

    List<SmartDevice> getAllByStatusAndUserId(Integer status, Long userId);

    Optional<SmartDevice> findByFcmToken(String fcmToken);
}
