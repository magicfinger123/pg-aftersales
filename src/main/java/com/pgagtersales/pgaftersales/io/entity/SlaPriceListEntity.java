/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "pricelist_sla")
@Data
public class SlaPriceListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(nullable = false, name = "rating")
    private String rating;
    @Column(nullable = false, name = "six_service")
    private String sixService;
    @Column(nullable = false, name = "eight_service")
    private String eightService;
    @Column(nullable = false, name = "ten_service")
    private String tenService;
    @Column(nullable = false, name = "twelve_service")
    private String twelveService;
}
