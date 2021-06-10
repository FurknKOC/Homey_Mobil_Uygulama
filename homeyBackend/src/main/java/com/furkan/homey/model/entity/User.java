package com.furkan.homey.model.entity;

import com.furkan.homey.model.type.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "uuser")
public class User extends BaseEntity{

    private final static String SEQUENCE_NAME = "user_id";
    public final static String JOIN_COLUMN = "user_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String about;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Contact contact;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserHouse userHouse;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserRoom userRoom;

    @OneToMany(mappedBy = "user")
    private List<Apart> aparts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Dormitory> dormitories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "userManager")
    private List<Complaint> complaintsManager = new ArrayList<>();

}
