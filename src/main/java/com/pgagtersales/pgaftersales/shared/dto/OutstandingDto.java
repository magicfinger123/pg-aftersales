/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class OutstandingDto {
    public static final long serialVersionUID = 6411563565645445491L;
    private int id;
    private String  clientId;
    private String clientName;
    private String companyName;
    private String description;
    private String invoicedAmount;
    private String amountPaid;
    private String user;
    private String lastFDate;
    private String balance;
    private String nextFDate;
    private String date;
    private int visible;
}
