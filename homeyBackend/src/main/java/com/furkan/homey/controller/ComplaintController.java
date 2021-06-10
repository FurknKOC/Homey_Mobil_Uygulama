package com.furkan.homey.controller;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.ComplaintDto;
import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComplaintController extends BaseController{

    private final ComplaintService complaintService;

    @PostMapping(CREATE_COMPLAINT)
    public String createApart(@RequestBody ComplaintDto complaintDto){

        return complaintService.createComplaint(complaintDto);
    }

    @PostMapping(GET_ALL_COMPLAINT)
    public List<ComplaintDto> getAllComplaintsByUser() {

        return complaintService.getAllComplaintsByUser();
    }

    @PostMapping(GET_ALL_COMPLAINT_MANAGER)
    public List<ComplaintDto> getAllComplaintByManager() {

        return complaintService.getAllComplaintsManager();
    }

    @PostMapping(COMPLAINT_TYPE_WORKING_ON)
    public String setComplaintTypeWorkingOn(@RequestBody ComplaintDto complaintDto) {

        return complaintService.setComplaintTypeWorkingOn(complaintDto);
    }

    @PostMapping(COMPLAINT_TYPE_SOLVED)
    public String setComplaintTypeSolved(@RequestBody ComplaintDto complaintDto) {

        return complaintService.setComplaintTypeSolved(complaintDto);
    }
}
