package com.furkan.homey.service;

import com.furkan.homey.model.dto.ToolDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.Tool;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.entity.UserHouse;
import com.furkan.homey.model.mapper.ToolMapper;
import com.furkan.homey.repository.ApartRepository;
import com.furkan.homey.repository.DormitoryRepository;
import com.furkan.homey.repository.ToolRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService extends BaseService {

    private final ToolRepository toolRepository;
    private final ApartRepository apartRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UserRepository userRepository;

    public String createTool(ToolDto toolDto) {

        Tool tool = new Tool();

        tool.setAbout(toolDto.getAbout());
        tool.setName(toolDto.getName());

        if (toolDto.getApartDto() != null) {
            tool.setApart(apartRepository.getOne(toolDto.getApartDto().getId()));
        } else if (toolDto.getDormitoryDto() != null) {
            tool.setDormitory(dormitoryRepository.getOne(toolDto.getDormitoryDto().getId()));
        }

        toolRepository.save(tool);

        return SUCCESS_RESPONSE;
    }

    public List<ToolDto> getTools() {

        User user = userRepository.findByUsername(getLoginUserName());
        UserHouse userHouse = user.getUserHouse();
        Apart apart = apartRepository.getOne(userHouse.getHouse().getApart().getId());

        List<Tool> tools = toolRepository.getAllByApartId(apart.getId());

        return ToolMapper.mapToDto(tools);
    }

}
