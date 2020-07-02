/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class InventoryItemDtoUpdate {
    private String inventoryItemId;
    private String inventoryId;
    private String itemName;
    private String itemUnits;
    private String message;
    private int itemQty;
}
