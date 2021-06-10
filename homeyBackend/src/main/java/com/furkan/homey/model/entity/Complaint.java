package com.furkan.homey.model.entity;

import com.furkan.homey.model.type.ComplaintType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Complaint extends BaseEntity{

    private final static String SEQUENCE_NAME = "complaint_id";
    public final static String JOIN_COLUMN = "complaint_id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME + "_gen")
    @SequenceGenerator(name = SEQUENCE_NAME + "_gen", sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String complaint;

    private String title;

    private Integer houseNumber;

    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userManager;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apart apart;
}
