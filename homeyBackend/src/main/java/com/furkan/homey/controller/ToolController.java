package com.furkan.homey.controller;

import com.furkan.homey.model.dto.ToolDto;
import com.furkan.homey.service.ToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolController extends BaseController{

    private final ToolService toolService;

    @PostMapping(CREATE_TOOL)
    public String createTool(@RequestBody ToolDto toolDto) {

       return toolService.createTool(toolDto);
    }

    @PostMapping(GET_TOOLS)
    public List<ToolDto> getTools() {

        return toolService.getTools();
    }

}
