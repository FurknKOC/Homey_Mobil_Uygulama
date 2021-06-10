package com.furkan.homey.model.entity;

import com.furkan.homey.model.type.ReservationTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation extends BaseEntity{

    private final static String SEQUENCE_NAME = "reservation_id";
    public final static String JOIN_COLUMN = "reservation_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private LocalDateTime date;

    private ReservationTime reservationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tool tool;

}
