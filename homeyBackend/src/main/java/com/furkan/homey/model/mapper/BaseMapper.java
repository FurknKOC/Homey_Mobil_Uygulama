package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.BaseDto;
import com.furkan.homey.model.entity.BaseEntity;

public class BaseMapper {
    public static void mapToDto(BaseDto dto, BaseEntity entity) {
        dto.setStatus(entity.getStatus());
        dto.setId(entity.getId());
    }

    public static void mapToEntity(BaseDto dto, BaseEntity entity) {
        entity.setStatus(dto.getStatus());
        entity.setId(dto.getId());
    }

}
