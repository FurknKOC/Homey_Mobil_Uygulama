package com.furkan.homey.service;

import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.mapper.UserMapper;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService {

    private final UserRepository userRepository;

    public String createUser(UserDto userDto) {

        User user = userRepository.findByUsername(userDto.getUsername());

        if (user == null) {
            User newUser = UserMapper.mapTo(userDto);
            userRepository.save(newUser);

            return SUCCESS_RESPONSE;
        }

        return FAIL_RESPONSE;
    }

    public UserDto getUserInformation() {

       User user = userRepository.findByUsername(getLoginUserName());

        return UserMapper.mapTo(user);
    }

}
