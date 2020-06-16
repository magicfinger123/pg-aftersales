/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScheduleByTeamId {
    private TeamDto teamDto;
    private List<ScheduleDto> scheduleDtos;
    private List<InventoryDto> inventoryDtos;
}
