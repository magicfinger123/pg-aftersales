/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name = "client_id", nullable = false)
    private String clientId;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "remark", nullable = false)
    private String remark;
    @Column(name = "priority")
    private String priority;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

}
