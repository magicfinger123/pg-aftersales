/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "inventory_items")
@Data
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(nullable = false, unique = true, name = "inventory_item_id")
    private String inventoryItemId;
    @Column(nullable = false, name = "inventory_id")
    private String inventoryId;
    @Column(nullable = false, name = "item_name")
    private String itemName;
    @Column(nullable = false, name = "item_units")
    private String itemUnits;
    @Column(nullable = false, name = "item_qty")
    private int itemQty;
}
