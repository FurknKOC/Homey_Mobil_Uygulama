package com.furkan.homey.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Apart extends BaseEntity{

    private final static String SEQUENCE_NAME = "apart_id";
    public final static String JOIN_COLUMN = "apart_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String address;

    private String name;

    private Integer houseCount;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "apart")
    private List<House> houses = new ArrayList<>();

    @OneToMany(mappedBy = "apart")
    private List<Tool> tools = new ArrayList<>();

    @OneToMany(mappedBy = "apart")
    private List<Complaint> complaints = new ArrayList<>();

}
