/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "schedule_task")
@Data
public class ScheduleTaskEntity {
    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false, unique = true, name = "task_id")
    private String taskId;

    @Column(nullable = false, name = "schedule_id")
    private String scheduleId;

    @Column(nullable = false, name = "task_name")
    private String taskName;

    @Column(nullable = false, name = "task_action")
    private String taskAction;

    @Column(nullable = false, name = "task_status")
    private boolean taskStatus;
}
