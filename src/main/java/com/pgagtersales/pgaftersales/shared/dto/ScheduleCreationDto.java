/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class ScheduleCreationDto implements Serializable {
    private String scheduleId;
    private String teamId;
    private String scheduleType;
    private String scheduleName;
    private boolean scheduleStatus;
}
