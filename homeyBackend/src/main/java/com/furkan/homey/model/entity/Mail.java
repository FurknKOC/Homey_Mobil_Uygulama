package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Table(name = "mmail")
public class Mail extends BaseEntity{

    private final static String SEQUENCE_NAME = "mail_id";
    public final static String JOIN_COLUMN = "mail_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "valueFrom")
    private String from;

    @Column(name = "valueTo")
    private String to;

    @Column(name = "valueSubject")
    private String subject;

    @Column(name = "valueContent")
    private String content;

}
