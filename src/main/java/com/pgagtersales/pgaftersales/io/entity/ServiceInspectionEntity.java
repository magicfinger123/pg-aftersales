/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "service_inspection")
@Data
public class ServiceInspectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String gen_id;

    @Column(nullable = false)
    private String technician;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, name = "hour_reading")
    private String hour_Reading;

    @Column(nullable = false, name = "next_service_hour")
    private String next_service;

    @Column(nullable = false, name = "service_count")
    private String service_count;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String remark;
}
