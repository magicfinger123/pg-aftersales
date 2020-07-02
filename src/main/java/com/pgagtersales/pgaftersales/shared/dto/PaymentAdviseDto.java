/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;
import lombok.Data;
@Data
public class PaymentAdviseDto {
    private String  clientId;
    private String genId;
    private Boolean generatorMaintenance;
    private String slaType;
    private String slaStartDate;
    private String servicesAlreadyDone;
    private String amountPaid;
    private String amountInvoiced;
    private String paymentType;
    private String paymentDescription;
    private String paymentItem;
}
