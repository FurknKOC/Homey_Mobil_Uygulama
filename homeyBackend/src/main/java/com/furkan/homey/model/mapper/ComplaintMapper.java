package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.ComplaintDto;
import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.entity.Complaint;
import com.furkan.homey.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ComplaintMapper {

    public static ComplaintDto mapTo(Complaint entity) {
        if (entity == null) {
            return null;
        }
        ComplaintDto dto = new ComplaintDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setComplaint(entity.getComplaint());
        dto.setComplaintType(entity.getComplaintType());
        dto.setUserDto(UserMapper.mapTo(entity.getUser()));
        dto.setUserManagerDto(UserMapper.mapTo(entity.getUserManager()));
        dto.setTitle(entity.getTitle());
        dto.setApartDto(ApartMapper.mapTo(entity.getApart()));
        dto.setHouseNumber(entity.getHouseNumber());

        return dto;
    }

    public static Complaint mapTo(ComplaintDto from, Complaint to) {
        BaseMapper.mapToEntity(from, to);
        to.setComplaint(from.getComplaint());
        to.setStatus(from.getStatus());
        to.setTitle(from.getTitle());
        to.setHouseNumber(from.getHouseNumber());

        return to;
    }

    public static Complaint mapTo(ComplaintDto dto) {
        return mapTo(dto, new Complaint());
    }

    public static List<ComplaintDto> mapToDto(List<Complaint> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(ComplaintMapper::mapTo).collect(Collectors.toList());
    }

    public static List<Complaint> mapToEntity(List<ComplaintDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(ComplaintMapper::mapTo).collect(Collectors.toList());
    }
}
