package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class House extends BaseEntity{

    private final static String SEQUENCE_NAME = "room_id";
    public final static String JOIN_COLUMN = "room_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private Integer houseNumber;

    @Column(name = "isItUsed", columnDefinition = "boolean default false")
    private boolean isItUsed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apart apart;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "house")
    private UserHouse userHouse;

}
