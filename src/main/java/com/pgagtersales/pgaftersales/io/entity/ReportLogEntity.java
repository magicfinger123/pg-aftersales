/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "reports_log")
@Data
public class ReportLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String userId;
    @Column(nullable = false)
    @Type(type = "text")
    private String description;
    @Column(nullable = false, length = 200)
    private String action;
    @Column(nullable = false, length = 100)
    private Date date;
    @Column(nullable = false, length = 100)
    private String status;
    @Column(nullable = false, length = 100)
    private String time;
}
