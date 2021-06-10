package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Contact extends BaseEntity{

    private final static String SEQUENCE_NAME = "contact_id";
    public final static String JOIN_COLUMN = "contact_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String email;

    private String phone;

    private String twitter;

    private String instagram;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
