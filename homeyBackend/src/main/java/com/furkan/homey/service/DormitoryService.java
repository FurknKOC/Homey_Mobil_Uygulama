package com.furkan.homey.service;

import com.furkan.homey.model.dto.DormitoryDto;
import com.furkan.homey.model.entity.Dormitory;
import com.furkan.homey.model.entity.Room;
import com.furkan.homey.model.mapper.DormitoryMapper;
import com.furkan.homey.repository.DormitoryRepository;
import com.furkan.homey.repository.RoomRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DormitoryService extends BaseService{

    private final DormitoryRepository dormitoryRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public String createDormitory(DormitoryDto dormitoryDto) {

        Dormitory dormitory = DormitoryMapper.mapTo(dormitoryDto);
        dormitory.setUser(userRepository.getOne(dormitoryDto.getUserDto().getId()));

        dormitory = dormitoryRepository.save(dormitory);

        for (int i = 0; i < dormitory.getRoomCount(); i++) {
            Room room = new Room();
            room.setDormitory(dormitory);
            room.setRoomNumber(i+1);

            roomRepository.save(room);
        }

        return SUCCESS_RESPONSE;
    }

}
