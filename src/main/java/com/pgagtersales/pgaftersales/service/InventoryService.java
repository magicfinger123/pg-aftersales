/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.InventoryCreationDto;
import com.pgagtersales.pgaftersales.model.resquest.ScheduleCreationDto;
import com.pgagtersales.pgaftersales.shared.dto.InventoryDto;
import com.pgagtersales.pgaftersales.shared.dto.InventoryItemDto;

public interface InventoryService {
    ApiResponse createInventory(InventoryCreationDto inventoryCreationDto);
    ApiResponse getAllInventory(int page, int size);
    ApiResponse getInventoryByTeamId(String teamId);
    ApiResponse getInventoryByScheduleId(String scheduleId);
    ApiResponse updateInventory(InventoryDto inventoryDto);
    ApiResponse addItemToInventory(InventoryItemDto inventoryItemDto);
    ApiResponse upDateInventoryItem(InventoryItemDto inventoryItemDto);
    ApiResponse deleteInventoryItem(String inventoryItemId);
    ApiResponse getItemsByInventoryId(String inventoryId);
}
