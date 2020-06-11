/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.sql.Date;


@Entity(name = "sla")
@Data
public class SlaEntity {
    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private int client_id;

    @Column(nullable = false)
    private int gen_id;

    @Column(nullable = false, length = 100, name = "sla_start_date")
    private Date slaStartDdate;

    @Column(nullable = false, length = 50,name="no_of_service")
    private int noOfService;

    @Column(nullable = false, length = 50, name = "hour_reading_start")
    private int hourReadingStart;

    @Column(nullable = false, length = 50, name="last_service_hour")
    private int lastServiceHour;

    @Column(nullable = false, length = 50, name = "last_service_date")
    private Date lastServiceDate;

    @Column(nullable = false, length = 50, name = "service_done")
    private int serviceDone;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;
}
