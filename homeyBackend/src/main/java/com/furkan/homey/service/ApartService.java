package com.furkan.homey.service;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.House;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.mapper.ApartMapper;
import com.furkan.homey.repository.ApartRepository;
import com.furkan.homey.repository.HouseRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApartService extends BaseService{

    private final UserRepository userRepository;
    private final ApartRepository apartRepository;
    private final HouseRepository houseRepository;

    public String createApart(ApartDto apartDto) {

        Apart apart = ApartMapper.mapTo(apartDto);
        apart.setUser(userRepository.getOne(apartDto.getUserDto().getId()));

        String uuid = UUID.randomUUID().toString();
        apart.setUuid(uuid);

        apart = apartRepository.save(apart);

        for (int i = 0; i < apart.getHouseCount(); i++) {
            House house = new House();
            house.setApart(apart);
            house.setHouseNumber(i+1);

            houseRepository.save(house);
        }

        return SUCCESS_RESPONSE;
    }

    public List<ApartDto> getAllApartByUser (UserDto userDto) {
        List<Apart> apartList = apartRepository.findByUserId(userDto.getId());

        List<ApartDto> apartDtoList = new ArrayList<>();

        for (Apart apart : apartList) {
            apartDtoList.add(ApartMapper.mapTo(apart));
        }

        return apartDtoList;
    }
}
