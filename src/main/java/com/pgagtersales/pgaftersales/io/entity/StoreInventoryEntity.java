/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "store_inventory")
@Data
public class StoreInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false, unique = true, name = "inventory_id")
    private String inventoryId;

    @Column(nullable = false, name = "inventory_type")
    private String inventoryType;

    @Column(name = "inventory_name", unique = true)
    private String inventoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "inventory_manager")
    private String inventoryManager;

    @Column(name = "last_update_user")
    private String updatedBy;


}
