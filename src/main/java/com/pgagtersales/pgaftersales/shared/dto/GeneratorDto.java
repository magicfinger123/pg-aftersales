package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Date;

@Data
public class GeneratorDto {
    public static final long serialVersionUID = 2828520025360413233L;
    private int id;
    private int client_idx;
    private String clientName;
    private String location;
    private String engineSerial;
    private String alternator_serial;
    private String alternator_type;
    private String engine_model;
    private String control_panel;
    private String usb_no;
    private String fuel_capacity;
    private String fuel_consumption_rate;
    private String size;
    private String generator_type;
    private String contact_person;
    private String contact_person_phone;
    private int maintenanceType;
    private Date maintenanceStartDate;
    private int serviceDone;
    private Date date;

    //private ClientDto clientDto;

}
