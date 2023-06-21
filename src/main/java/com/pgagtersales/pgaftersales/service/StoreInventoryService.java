package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.*;

public interface StoreInventoryService {
    ApiResponse createInventory(StoreInventoryDto inventoryDto);
    ApiResponse getAllInventory(int page, int size);
    ApiResponse updateInventory(StoreInventoryDto inventoryDto);
    ApiResponse addItemToInventory(StoreInventoryItemDto inventoryItemDto);
    ApiResponse upDateInventoryItem(StoreInventoryItemDto inventoryItemDto);
    ApiResponse deleteInventoryItem(String inventoryItemId);
    ApiResponse getItemsByInventoryIds(UpdateInventoryItemDto inventoryItemDtos);
    ApiResponse getItemsByInventoryId(String inventoryId);
}
