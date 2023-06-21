/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "store_inventory_items")
@Data
public class StoreInventoryItemEntity {

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

    @Column(name = "image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String imageBase64;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

    @Column(name = "last_update_user")
    private String updatedBy;

}

