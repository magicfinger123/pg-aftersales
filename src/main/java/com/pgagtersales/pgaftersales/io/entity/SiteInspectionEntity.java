/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "siteInspection")
@Data
public class SiteInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String genLocation;

    @Column(nullable = false, length = 100)
    private String roadProximity;

    @Column(nullable = false, length = 100)
    private String genHouse;

    @Column(nullable = false, length = 100)
    private String genHiab;

    @Column(nullable = false)
    private String genCable;

    @Column(nullable = false)
    private String genExhaust;

    @Column(nullable = false)
    private String genForklift;

    @Column(nullable = false)
    private String genMinload;

    @Column(nullable = false)
    private String genMaxload;

    @Column(nullable = false)
    private String genInstallation;

    @Column(nullable = false)
    private String genClientPreference;

    @Column(nullable = false)
    private String genYourrecom;

    @Column(nullable = false)
    private String genCivilWorks;

    @Column(nullable = false)
    private String genExternalTank;

    @Column(nullable = false, length = 100)
    private String genCommissionDate;
}
