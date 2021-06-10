package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Tool extends BaseEntity{

    private final static String SEQUENCE_NAME = "tool_id";
    public final static String JOIN_COLUMN = "tool_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String name;

    private String about;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apart apart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dormitory dormitory;

    @OneToMany(mappedBy = "tool")
    private List<Reservation> reservationList = new ArrayList<>();
}
