package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class StoreInventoryItemDto {
        @Nullable
        private String inventoryItemId;
        private String inventoryId;
        private String inventoryManager;
        private String itemName;
        private String itemUnits;
        private int itemQty;
        private String imageBase64;
        private String price;
        private String description;
        @Nullable
        private String updatedBy;


}
