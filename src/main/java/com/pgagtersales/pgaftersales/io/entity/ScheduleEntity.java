/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "work_schedule")
@Data
public class ScheduleEntity {
    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(nullable = false, unique = true, name = "schedule_id")
    private String scheduleId;
    @Column(nullable = false, name = "team_id")
    private String teamId;
    @Column(nullable = false, name = "schedule_type")
    private String scheduleType;
    @Column(nullable = false, name = "schedule_name")
    private String scheduleName;
    @Column(nullable = false, name = "schedule_status")
    private boolean scheduleStatus;
}
