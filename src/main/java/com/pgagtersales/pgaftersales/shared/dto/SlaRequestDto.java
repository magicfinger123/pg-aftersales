/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class SlaRequestDto {
    private String client_no;
    private String client_email;
    private String clientName;
    private String clientAddress;
    private String genId;
    private String genLocation;
    private String genSize;
    private String genCategory;
    private String engineSerial;
    private String genType;
    private String slaType;
    private String slaStart;
    private String noOfServicesDone;
    private String amount;
    private Boolean renewal;
    /*
    * private String clientName;
    private String genId;
    private String genSize;
    private String engineSerial;
    private String genType;
    private String slaType;
    private String amountPaid;
    private String paymentType;
    private String slaStart;
    private String noOfServicesDone;*/
}
