package com.furkan.homey.service.security;

import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DummyUserService {

    private final UserRepository userRepository;

    private Map<String, User> users = new HashMap<>();

    public User getUserByUsername(String username) {
        com.furkan.homey.model.entity.User user = userRepository.findByUsername(username);
        users.put(user.getUsername(), new User(user.getUsername(), user.getPassword(), new ArrayList<>()));
        return users.get(username);
    }
}