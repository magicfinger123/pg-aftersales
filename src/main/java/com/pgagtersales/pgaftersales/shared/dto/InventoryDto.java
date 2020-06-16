/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.annotation.Nullable;
import javax.persistence.Column;

@Data
public class InventoryDto {
    private String inventoryId;
    private String inventoryType;
    @Nullable
    private String scheduleId;
    private String teamId;
    private String inventoryName;
    private String inventoryManager;
}
