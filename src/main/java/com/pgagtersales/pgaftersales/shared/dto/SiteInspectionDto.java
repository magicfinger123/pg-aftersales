/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import java.util.List;

@Data
public class SiteInspectionDto {
    public String  clientEmail;
    public String  clientId;
    public String  clientName;
    private String genLocation;
    private String roadProximity;
    private String genClientPreference;
    private String genYourrecom;
    private String genCommissionDate;
    private String contactPerson;
    private String contactPersonPhone;
    private String inspector;
    private List<GeneratorRequirement> generatorRequirements;
    public List<GeneratorRequirement> getGeneratorRequirements() {
        return generatorRequirements;
    }
}
