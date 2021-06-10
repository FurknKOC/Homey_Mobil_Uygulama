package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserHouse extends BaseEntity {

    private final static String SEQUENCE_NAME = "user_house_id";
    public final static String JOIN_COLUMN = "user_house_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private House house;

}
