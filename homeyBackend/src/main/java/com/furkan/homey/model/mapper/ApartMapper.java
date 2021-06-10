package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ApartMapper extends BaseMapper{

    public static ApartDto mapTo(Apart entity) {
        if (entity == null) {
            return null;
        }
        ApartDto dto = new ApartDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setHouseCount(entity.getHouseCount());
        dto.setAddress(entity.getAddress());
        dto.setName(entity.getName());

        return dto;
    }

    public static Apart mapTo(ApartDto from, Apart to) {
        BaseMapper.mapToEntity(from, to);
        to.setHouseCount(from.getHouseCount());
        to.setAddress(from.getAddress());
        to.setName(from.getName());
        to.setStatus(from.getStatus());

        return to;
    }

    public static Apart mapTo(ApartDto dto) {
        return mapTo(dto, new Apart());
    }

    public static List<ApartDto> mapToDto(List<Apart> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(ApartMapper::mapTo).collect(Collectors.toList());
    }

    public static List<Apart> mapToEntity(List<ApartDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(ApartMapper::mapTo).collect(Collectors.toList());
    }
}
