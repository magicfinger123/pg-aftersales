/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "inventory")
@Data
public class InventoryEntity {

    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false, unique = true, name = "inventory_id")
    private String inventoryId;

    @Column(nullable = false, name = "inventory_type")
    private String inventoryType;

    @Column(name = "schedule_id")
    private String scheduleId;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "inventory_name", unique = true)
    private String inventoryName;

    @Column(name = "inventory_manager")
    private String inventoryManager;
}
