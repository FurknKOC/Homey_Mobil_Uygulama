package com.furkan.homey.service;

import com.furkan.homey.model.dto.UserHouseDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.House;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.entity.UserHouse;
import com.furkan.homey.repository.ApartRepository;
import com.furkan.homey.repository.HouseRepository;
import com.furkan.homey.repository.UserHouseRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHouseService extends BaseService{

    private final ApartRepository apartRepository;
    private final HouseRepository houseRepository;
    private final UserHouseRepository userHouseRepository;
    private final UserRepository userRepository;

    public String registerHouseUser (UserHouseDto userHouseDto) {

        Apart apart = apartRepository.findApartByUuid(userHouseDto.getUuid());
        User user = userRepository.findByUsername(getLoginUserName());
        House house = houseRepository.findHouseByApartIdAndHouseNumber(apart.getId(),userHouseDto.getHouseNumber());

        if (!house.isItUsed()) {
            UserHouse userHouse = new UserHouse();
            userHouse.setHouse(house);
            userHouse.setUser(user);

            userHouseRepository.save(userHouse);

            return SUCCESS_RESPONSE;
        } else {
            return FAIL_RESPONSE;
        }
    }

}
