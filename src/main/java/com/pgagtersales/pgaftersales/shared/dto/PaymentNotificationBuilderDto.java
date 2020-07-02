/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class PaymentNotificationBuilderDto {
    private String clientName;
    private String slaType;
    private String genSize;
    private int amountPaid;
    private int balance;
    private String paymentType;
    private String paymentDescription;
    private int currentOutstandingOwed;
    private String outStandingDescription;
    private int totalBalance = balance+currentOutstandingOwed;
    private String slaStartDate;
    private String servicesAlreadyDone;
}
