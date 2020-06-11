/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class BreakdownReportDto {
    private String firstName;
    private String streetAdd;
    private String engineSerial;
    private String alternatorSerial;
    private String generatorSize;
    private String hourReading;
    private String nextServiceHour;
    private String rootCause;
    private String breakdownDescription;
    private String previousAction;
    private String actionTaken;
    private String pendingAction;
    private String recommendation;
    private String genISUsable;
    private String timeIn;
    private String timeOut;
    private String date;
    private String comment;
    private String isfollowUp;
    private String technician;
}
