package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "generators")
@Data
public class GeneratorEntity {
    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, length = 50)
    private String engineSerial;

    @Column(nullable = false, length = 50)
    private String alternator_serial;

    @Column(nullable = false, length = 50)
    private String alternator_type;

    @Column(nullable = false, length = 50)
    private String engine_model;

    @Column(nullable = false, length = 50)
    private String control_panel;

    @Column(nullable = false, length = 50)
    private String usb_no;

    @Column(nullable = false, length = 100)
    private String fuel_capacity;

    @Column(nullable = false, length = 50)
    private String fuel_consumption_rate;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false, length = 100)
    private String generator_type;

    @Column(nullable = false, length = 50)
    private String contact_person;

    @Column(nullable = false)
    private String contact_person_phone;

    @Column(nullable = false, name = "purchase_year")
    private Date date;
/*    @ManyToOne(fetch = FetchType.LAZY)
    private ClientsEntity clientDto;*/
}
