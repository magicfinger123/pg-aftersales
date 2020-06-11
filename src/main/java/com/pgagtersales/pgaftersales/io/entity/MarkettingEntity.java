/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;

@Entity(name = "prospect_client")
@Data
public class MarkettingEntity {
    public static final long serialVersionUID = 64115635656454454L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String recommendation;

    @Column(nullable = false, name = "powerrequirement")
    private String powerRequirement;

    @Column(nullable = false)
    private String companyname;

    @Column(nullable = false)
    private String contactname;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "ordertype")
    private String interest;

    @Column(nullable = false, name = "additionalInfo")
    private String additionalInformation;

    @Column(nullable = false, name = "date")
    private String dateOfVisit;

    @Column(nullable = false)
    private String salesPerson;

    @Column(nullable = false)
    private Boolean status = true;
}
