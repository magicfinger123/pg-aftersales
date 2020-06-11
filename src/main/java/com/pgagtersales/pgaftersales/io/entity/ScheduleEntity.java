/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "workschedule")
public class ScheduleEntity {
    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false, unique = true, name = "schedule_id")
    private int scheduleId;

    @Column(nullable = false, name = "schedule_type")
    private int scheduleType;
}
