/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class TicketsDto {
    public static final long serialVersionUID = 71687151379797464L;
    private int id;
    private String clientName;
    private String companyName;
    private String description;
    private String clientId;
    private int Status;
    private String remark;
    private String priority;
    private String startDate;
    private String endDate;
}
