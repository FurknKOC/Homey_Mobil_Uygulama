package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Room extends BaseEntity{

    private final static String SEQUENCE_NAME = "room_id";
    public final static String JOIN_COLUMN = "room_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private Integer roomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dormitory dormitory;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "room")
    private UserRoom userRoom;

}
