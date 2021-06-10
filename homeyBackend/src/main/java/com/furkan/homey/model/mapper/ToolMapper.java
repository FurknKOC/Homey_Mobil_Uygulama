package com.furkan.homey.model.mapper;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.ToolDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.Tool;

import java.util.List;
import java.util.stream.Collectors;

public class ToolMapper extends BaseMapper{

    public static ToolDto mapTo(Tool entity) {
        if (entity == null) {
            return null;
        }
        ToolDto dto = new ToolDto();
        BaseMapper.mapToDto(dto, entity);

        dto.setAbout(entity.getAbout());
        dto.setName(entity.getName());

        return dto;
    }

    public static Tool mapTo(ToolDto from, Tool to) {
        BaseMapper.mapToEntity(from, to);
        to.setAbout(from.getAbout());
        to.setName(from.getName());
        to.setStatus(from.getStatus());

        return to;
    }

    public static Tool mapTo(ToolDto dto) {
        return mapTo(dto, new Tool());
    }

    public static List<ToolDto> mapToDto(List<Tool> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(ToolMapper::mapTo).collect(Collectors.toList());
    }

    public static List<Tool> mapToEntity(List<ToolDto> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(ToolMapper::mapTo).collect(Collectors.toList());
    }
}
