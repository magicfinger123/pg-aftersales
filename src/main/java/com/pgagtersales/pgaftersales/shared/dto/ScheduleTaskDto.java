/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ScheduleTaskDto {

    private String taskId;
    private String scheduleId;
    private String taskName;
    private String taskAction;
    private boolean taskStatus;
}
