package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notification extends BaseEntity{

    private final static String SEQUENCE_NAME = "notification_id";
    public final static String JOIN_COLUMN = "notification_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private Long userId;

    private Boolean read = Boolean.FALSE;

    private Boolean success;

    @Column(length = 4000)
    private String message;

    @Column(length = 200)
    private String title;

    @Column(length = 4000)
    private String datum;

    @Column(columnDefinition = "TEXT")
    private String response;

    public Notification setAsRead(){
        this.read = Boolean.TRUE;
        return this;
    }

    public Notification setAsUnread(){
        this.read = Boolean.FALSE;
        return this;
    }

}
