package com.pgagtersales.pgaftersales.shared.dto;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class StoreInventoryDto {

    @Nullable
    private String inventoryId;
    private String inventoryType;
    private String inventoryName;
    private String description;
    private String inventoryManager;
    @Nullable
    private String updatedBy;
}
