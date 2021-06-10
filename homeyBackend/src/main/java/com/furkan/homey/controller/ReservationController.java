package com.furkan.homey.controller;

import com.furkan.homey.model.dto.ReservationDto;
import com.furkan.homey.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController extends BaseController{

    private final ReservationService reservationService;

    @PostMapping(CREATE_RESERVATION)
    public String createReservation(@RequestBody ReservationDto reservationDto) {

        return reservationService.createReservation(reservationDto);
    }

    @PostMapping(GET_RESERVATIONS)
    public List<String> getReservations(@RequestBody ReservationDto reservationDto) {

        return reservationService.getReservations(reservationDto);
    }

}
