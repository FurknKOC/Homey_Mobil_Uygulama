package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.DormitoryDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.Dormitory;

import java.util.List;
import java.util.stream.Collectors;

public class DormitoryMapper extends BaseMapper{

    public static DormitoryDto mapTo(Dormitory entity) {
        if (entity == null) {
            return null;
        }
        DormitoryDto dto = new DormitoryDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setRoomCount(entity.getRoomCount());
        dto.setAddress(entity.getAddress());
        dto.setName(entity.getName());
        dto.setRoomCount(entity.getRoomCount());

        return dto;
    }

    public static Dormitory mapTo(DormitoryDto from, Dormitory to) {
        BaseMapper.mapToEntity(from, to);
        to.setRoomCount(from.getRoomCount());
        to.setAddress(from.getAddress());
        to.setName(from.getName());
        to.setRoomCount(from.getRoomCount());
        to.setStatus(from.getStatus());

        return to;
    }

    public static Dormitory mapTo(DormitoryDto dto) {
        return mapTo(dto, new Dormitory());
    }

    public static List<DormitoryDto> mapToDto(List<Dormitory> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(DormitoryMapper::mapTo).collect(Collectors.toList());
    }

    public static List<Dormitory> mapToEntity(List<DormitoryDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(DormitoryMapper::mapTo).collect(Collectors.toList());
    }
}
