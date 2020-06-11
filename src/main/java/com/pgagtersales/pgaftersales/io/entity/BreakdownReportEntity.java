/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "breakdown_report")
@Data
public class BreakdownReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String streetAdd;

    @Column(nullable = false)
    private String engineSerial;

    @Column(nullable = false)
    private String alternatorSerial;

    @Column(nullable = false)
    private String generatorSize;

    @Column(nullable = false)
    private String hourReading;

    @Column(nullable = false)
    private String nextServiceHour;

    @Column(nullable = false)
    private String rootCause;

    @Column(nullable = false)
    private String breakdownDescription;

    @Column(nullable = false)
    private String previousAction;

    @Column(nullable = false)
    private String actionTaken;

    @Column(nullable = false)
    private String pendingAction;

    @Column(nullable = false)
    private String recommendation;

    @Column(nullable = false)
    private String genISUsable;

    @Column(nullable = false)
    private String timeIn;

    @Column(nullable = false)
    private String timeOut;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String isfollowUp;

    @Column(nullable = false)
    private String technician;
}
