/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class MarkettingDto implements Serializable {
    private String recommendation;
    private String powerRequirement;
    private String companyname;
    private String contactname;
    private String address;
    private String phone;
    private String email;
    private String interest;
    private String additionalInformation;
    private String dateOfVisit;
    private String salesPerson;
}

