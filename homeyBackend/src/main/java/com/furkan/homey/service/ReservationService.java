package com.furkan.homey.service;

import com.furkan.homey.model.dto.ReservationDto;
import com.furkan.homey.model.entity.Reservation;
import com.furkan.homey.model.entity.User;
import com.furkan.homey.model.mapper.ReservationMapper;
import com.furkan.homey.repository.ReservationRepository;
import com.furkan.homey.repository.ToolRepository;
import com.furkan.homey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService extends BaseService{

    private final ToolRepository toolRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public String createReservation(ReservationDto reservationDto) {

        User user = userRepository.findByUsername(getLoginUserName());

        Reservation reservation = ReservationMapper.mapTo(reservationDto);

        reservation.setTool(toolRepository.getOne(reservationDto.getToolDto().getId()));
        reservation.setUser(user);

        reservationRepository.save(reservation);

        return SUCCESS_RESPONSE;
    }

    public List<String> getReservations(ReservationDto reservationDto) {

        LocalDateTime endDate = reservationDto.getDateTime().plusDays(1);
        LocalDateTime startDate = reservationDto.getDateTime().minusDays(1);

        List<Reservation> reservations = reservationRepository.getReservationsByDateAndTool(startDate, endDate,reservationDto.getToolDto().getId());

        List<String> timeOptions = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getDate().getDayOfMonth() == reservationDto.getDateTime().getDayOfMonth())
            {
                timeOptions.add(reservation.getReservationTime().name());
            }
        }

        timeOptions = timeOptions.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

        return timeOptions;
    }

}
