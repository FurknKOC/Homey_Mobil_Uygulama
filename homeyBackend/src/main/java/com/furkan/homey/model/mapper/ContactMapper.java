package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.ContactDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper extends BaseMapper{

    public static ContactDto mapTo(Contact entity) {
        if (entity == null) {
            return null;
        }
        ContactDto dto = new ContactDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setEmail(entity.getEmail());
        dto.setInstagram(entity.getInstagram());
        dto.setPhone(entity.getPhone());
        dto.setTwitter(entity.getTwitter());

        return dto;
    }

    public static Contact mapTo(ContactDto from, Contact to) {
        BaseMapper.mapToEntity(from, to);
        to.setEmail(from.getEmail());
        to.setInstagram(from.getInstagram());
        to.setPhone(from.getPhone());
        to.setTwitter(from.getTwitter());
        to.setStatus(from.getStatus());

        return to;
    }

    public static Contact mapTo(ContactDto dto) {
        return mapTo(dto, new Contact());
    }

    public static List<ContactDto> mapToDto(List<Contact> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(ContactMapper::mapTo).collect(Collectors.toList());
    }

    public static List<Contact> mapToEntity(List<ContactDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(ContactMapper::mapTo).collect(Collectors.toList());
    }
}
