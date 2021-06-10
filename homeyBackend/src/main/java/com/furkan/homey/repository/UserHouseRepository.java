package com.furkan.homey.repository;

import com.furkan.homey.model.entity.UserHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHouseRepository extends JpaRepository<UserHouse, Long> {

    UserHouse findByUserId(Long userId);

}
