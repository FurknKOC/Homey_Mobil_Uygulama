package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper extends BaseMapper{

    public static UserDto mapTo(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setAbout(entity.getAbout());
        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setSurname(entity.getSurname());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());

        return dto;
    }

    public static User mapTo(UserDto from, User to) {
        BaseMapper.mapToEntity(from, to);
        to.setAbout(from.getAbout());
        to.setName(from.getName());
        to.setRole(from.getRole());
        to.setSurname(from.getSurname());
        to.setPassword(from.getPassword());
        to.setUsername(from.getUsername());
        to.setStatus(from.getStatus());
        to.setEmail(from.getEmail());
        to.setPhone(from.getPhone());

        return to;
    }

    public static User mapTo(UserDto dto) {
        return mapTo(dto, new User());
    }

    public static List<UserDto> mapToDto(List<User> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(UserMapper::mapTo).collect(Collectors.toList());
    }

    public static List<User> mapToEntity(List<UserDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(UserMapper::mapTo).collect(Collectors.toList());
    }
}
