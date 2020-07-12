/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class GeneratorInspectionDto {
    private String client_id;
    private String client_email;
    private String gen_id;
    private String technician;
    private String hour_Reading;
    private String next_service;
    private String date;
    private String remark;
    private String engineSerialNumber;
    private String technician2;
    private String timeIn;
    private String timeOut;
    private String oilPressure;
    private String temperature;
    private String alternatorNum;
    private String generatorSize;
    private String genAddress;
    private String clientName;
    private String verifyFuelLeakage;
    private String fluidLevel;
    private String fanbeltCheck;
    private String oilLeakage;
    private String acAndDcConnection;
    private String batteryCheck;
    private String chargingAlternatoreCheck;
    private String kickStarterCheck;
    private String testONload;
    private String frequencyCheck;
    private String status;
    private String service_count;
}
