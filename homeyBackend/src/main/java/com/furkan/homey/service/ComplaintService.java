package com.furkan.homey.service;

import com.furkan.homey.model.dto.ApartDto;
import com.furkan.homey.model.dto.ComplaintDto;
import com.furkan.homey.model.dto.PushNotificationRequestDto;
import com.furkan.homey.model.dto.UserDto;
import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.Complaint;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.entity.UserHouse;
import com.furkan.homey.model.mapper.ApartMapper;
import com.furkan.homey.model.mapper.ComplaintMapper;
import com.furkan.homey.model.type.ComplaintType;
import com.furkan.homey.repository.ApartRepository;
import com.furkan.homey.repository.ComplaintRepository;
import com.furkan.homey.repository.UserHouseRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService extends BaseService {

    private final UserRepository userRepository;
    private final ApartRepository apartRepository;
    private final ComplaintRepository complaintRepository;
    private final UserHouseRepository userHouseRepository;
    private final PushNotificationService pushNotificationService;

    public String createComplaint(ComplaintDto complaintDto) {

        User user = userRepository.findByUsername(getLoginUserName());
        UserHouse userHouse = userHouseRepository.findByUserId(user.getId());
        Apart apart = apartRepository.getOne(userHouse.getHouse().getApart().getId());
        User managerUser = userRepository.getOne(apart.getUser().getId());


        Complaint complaint = new Complaint();

        complaint.setComplaint(complaintDto.getComplaint());
        complaint.setTitle(complaintDto.getTitle());
        complaint.setComplaintType(ComplaintType.ENTRY);
        complaint.setHouseNumber(userHouse.getHouse().getHouseNumber());
        complaint.setApart(apart);
        complaint.setUser(user);
        complaint.setUserManager(managerUser);

        PushNotificationRequestDto pushNotificationRequestDto = new PushNotificationRequestDto();
        pushNotificationRequestDto.setTitle("Homey");
        pushNotificationRequestDto.setMessage("Yeni Bir Şikayet Var!");
        pushNotificationRequestDto.setAccountId(managerUser.getId());
        pushNotificationService.sendPushNotification(pushNotificationRequestDto);

        complaintRepository.save(complaint);

    return SUCCESS_RESPONSE;
   }

   public List<ComplaintDto> getAllComplaintsByUser() {
       User user = userRepository.findByUsername(getLoginUserName());
       List<Complaint> complaintList = complaintRepository.findByUserId(user.getId());

       return ComplaintMapper.mapToDto(complaintList);
   }

    public List<ComplaintDto> getAllComplaintsManager() {
        User user = userRepository.findByUsername(getLoginUserName());
        List<Complaint> complaintList = complaintRepository.findByUserManagerId(user.getId());

        return ComplaintMapper.mapToDto(complaintList);
    }

    public String setComplaintTypeWorkingOn(ComplaintDto complaintDto) {

        Complaint complaint = complaintRepository.getOne(complaintDto.getId());
        complaint.setComplaintType(ComplaintType.WORKING_ON);
        complaintRepository.save(complaint);

        return SUCCESS_RESPONSE;
    }

    public String setComplaintTypeSolved(ComplaintDto complaintDto) {

        Complaint complaint = complaintRepository.getOne(complaintDto.getId());
        complaint.setComplaintType(ComplaintType.SOLVED);
        complaintRepository.save(complaint);

        return SUCCESS_RESPONSE;
    }

}
