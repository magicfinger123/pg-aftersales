package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class GeneratorDto {
    public static final long serialVersionUID = 2828520025360413233L;
    private int id;
    private int client_id;
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
    private String purchase_year;
}
