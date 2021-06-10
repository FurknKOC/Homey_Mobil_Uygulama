package com.furkan.homey.repository;

import com.furkan.homey.model.entity.Reservation;
import com.furkan.homey.model.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r " +
            "where r.date between :startDate and :endDate " +
            "and r.tool.id =:toolId")
    List<Reservation> getReservationsByDateAndTool(LocalDateTime startDate, LocalDateTime endDate, Long toolId);

}
