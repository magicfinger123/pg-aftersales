/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.resquest;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleCreationDto {
    private String teamId;
    private String scheduleType;
    private String scheduleName;
    private boolean scheduleStatus;
    private List<ScheduleTaskCreationDto> taskCreationDtos;
}
