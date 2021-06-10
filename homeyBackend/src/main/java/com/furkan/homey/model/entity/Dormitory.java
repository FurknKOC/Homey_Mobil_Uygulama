package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Dormitory extends BaseEntity{

    private final static String SEQUENCE_NAME = "dormitory_id";
    public final static String JOIN_COLUMN = "dormitory_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String address;

    private String name;

    private Integer roomCount;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "dormitory")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "dormitory")
    private List<Tool> tools = new ArrayList<>();
}
