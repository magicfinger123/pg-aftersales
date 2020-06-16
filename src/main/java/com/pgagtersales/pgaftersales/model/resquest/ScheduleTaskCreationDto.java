/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.resquest;

import lombok.Data;

@Data
public class ScheduleTaskCreationDto {
    private String scheduleId;
    private String taskName;
    private String taskAction;
    private boolean taskStatus;
}
