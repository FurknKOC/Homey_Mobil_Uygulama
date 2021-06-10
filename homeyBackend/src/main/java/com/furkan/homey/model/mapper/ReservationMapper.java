package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.ReservationDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper extends BaseMapper{

    public static ReservationDto mapTo(Reservation entity) {
        if (entity == null) {
            return null;
        }
        ReservationDto dto = new ReservationDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setDateTime(entity.getDate());
        dto.setReservationTime(entity.getReservationTime());

        return dto;
    }

    public static Reservation mapTo(ReservationDto from, Reservation to) {
        BaseMapper.mapToEntity(from, to);
        to.setDate(from.getDateTime());
        to.setReservationTime(from.getReservationTime());
        to.setStatus(from.getStatus());

        return to;
    }

    public static Reservation mapTo(ReservationDto dto) {
        return mapTo(dto, new Reservation());
    }

    public static List<ReservationDto> mapToDto(List<Reservation> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(ReservationMapper::mapTo).collect(Collectors.toList());
    }

    public static List<Reservation> mapToEntity(List<ReservationDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(ReservationMapper::mapTo).collect(Collectors.toList());
    }
}
