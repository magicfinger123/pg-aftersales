/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name = "team_id")
    private String teamId;
    @Column(name = "team_title", nullable = false)
    private String teamTitle;
    @Column(name = "team_dept", nullable = false)
    private String teamDept;
    @Column(name = "team_lead", nullable = false)
    private String teamLead;
    @Column(name = "team_member_1")
    private String teamMember1;
    @Column(name = "team_member_2")
    private String teamMember2;
    @Column(name = "team_member_3")
    private String teamMember3;
    @Column(name = "team_member_4")
    private String teamMember4;
}
