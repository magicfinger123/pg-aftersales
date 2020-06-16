/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.resquest;

import lombok.Data;

@Data
public class InventoryCreationDto {
    private String inventoryType;
    private String scheduleId;
    private String teamId;
    private String inventoryName;
    private String inventoryManager;
}
