/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.messages;

import com.pgagtersales.pgaftersales.service.GeneratorInspectionService;
import com.pgagtersales.pgaftersales.shared.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMessages {
    public String addGenNotification(GeneratorDto generatorDto) {
        String message = "<style>" +
                "</style>" +
                "<p>Site Inspection Report </p><br>" +
                "<Table class=\"table-striped\" style:  \"border-spacing: 0;" +
                "  border-collapse: collapse; border: 1px solid #ddd !important;\">" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Item: </th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Details: </th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Date Of Commissioning:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"> " + generatorDto.getPurchase_year() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Engine Serial Number:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getEngineSerial() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Engine Model:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getEngine_model() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Alternator Serial: </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getAlternator_serial() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Alternator Type:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getAlternator_type() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Size:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getSize() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Location:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getLocation() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr >" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Control Panel</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getControl_panel() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Usb No</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getUsb_no() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Manufacturer:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getGenerator_type() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Fuel Tank Capacity(Base Tank):</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getFuel_capacity() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Fuel Consumption rate / hr:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getFuel_consumption_rate() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Contact Person On site</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getContact_person() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Contact Person on Site Phone Number:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getContact_person_phone() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Date of Commissioning:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorDto.getPurchase_year() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "</table>" +

                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">For Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:12pt; color: Red;\">This report was generated runtime from the field</p>" +
                "<p  style=\"font-family: verdana; font-size:11pt;font-style:italic\">user details:</p>" +
                "<ul>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Username: PgAdmin</li>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Role: Admin </li>" +
                "</ul>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: Powergenltd.com</p>";
        return message;
    }
    public String siteInspectionNotification(SiteInspectionDto siteInspection){
        List<GeneratorRequirement> generatorRequirements = siteInspection.getGeneratorRequirements();
        StringBuilder strBuilder = new StringBuilder();
        int index = 1;
        for (GeneratorRequirement generatorRequirement : generatorRequirements) {
            strBuilder.append(
                    "<h1>Generator "+index++ +"</h1>"+
                    "<tr style=\"; background-color: #f9f9f9\">" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Generator Size</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getGeneratorSize()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<tr >" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Generator Base Requirement</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getGeneratorBase()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<p> Cable Requirement </p>"+
                            "<tr style=\"; background-color: #f9f9f9\">" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Cable Length</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getCableLength()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<tr >" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Cable Size</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getCableSize()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>"+
                            "<tr >" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Change Over Requirement</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getChangeOverSize()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>"+
                            "<p> Panel Requirement </p>"+
                            "<tr style=\"; background-color: #f9f9f9\">" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Panel Requirement</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getPanelRequirement()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<p> Earthing Requirement </p>"+
                            "<tr style=\"; background-color: #f9f9f9\">" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Earth Rod Length</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getEarthRodLength()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<tr >" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Earth Rod Size</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getEarthRodSize()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>"+
                            "<p> Exhaust and External Tank requirement </p>"+
                            "<tr style=\"; background-color: #f9f9f9\">" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">External Tank Requirement</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getExternalTankRequirement()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<tr >" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Exhaust Requirement</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getExhaustRrquirement()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>"+
                            "<p> Others </p>"+
                            "<tr style=\"; background-color: #f9f9f9\">" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Civil works </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getCivilWorkRequirement()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>" +
                            "<tr >" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">Other Installation materials needed</td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\">"+generatorRequirement.getOtherInstallation()+" </td>" +
                            "<td style=\"padding: 8px;\n" +
                            "  line-height: 1.42857143;\n" +
                            "  vertical-align: top;\n" +
                            "  border-top: 1px solid #ddd\"></td>" +
                            "</tr>"
            );
        }
        String message = "<style>" +
                "</style>" +
                "<p>Site Inspection Report </p><br>" +
                "<Table class=\"table-striped\" style:  \"border-spacing: 0;" +
                "  border-collapse: collapse; border: 1px solid #ddd !important;\">" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Items: </th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Details: </th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Client Name:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">"+siteInspection.getClientName()+" </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">client Email:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getClientEmail()+ " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Contact Person on Site:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">"+siteInspection.getContactPerson()+" </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Contact Person Phone</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getContactPersonPhone()+ " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Inspection Date:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">"+siteInspection.getGenCommissionDate()+" </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Site Location:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getGenLocation()+ " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Proximity and Availability of access roads:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" +siteInspection.getRoadProximity()+ " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
               /* "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Accessibility to Hiab: </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getGenHiab() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Installation cable length and size</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getGenCable() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +*/
               strBuilder.toString()+
                //end of modification
                "<h2>Client Preference and Inspector Recommendation</h2>"+
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Clients Preference:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getGenClientPreference() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Inspector Recommendation:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + siteInspection.getGenYourrecom() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "</table>" +
                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">For Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:12pt; color: Red;\">Powergen Customer Experience Center</p>" +
                "<ul>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Inspecting officer: "+siteInspection.getInspector()+"</li>" +
                "</ul>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: https://www.powergenltd.com</p>";
        return message;
    }
    public String serviceReportNotification(ServiceInspectionDto serviceInspectionDto){
        final String message = "<style>" +
                "</style>" +
                "Please find below, details of service<br>" +
                "<Table class=\"table-striped\" style:  \"border-spacing: 0;" +
                "  border-collapse: collapse; border: 1px solid #ddd !important;\">" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Customer name:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getClientName() + "</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Location:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getGenAddress() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Engine Serial:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getEngineSerialNumber() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Alternator type:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getAlternatorNum() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Gen capacity:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getGeneratorSize() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Hour Reading:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getHour_Reading() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Next Service Hour:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getNext_service() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">No of Service Done</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getService_count() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Engine oil:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getOilType() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getOilQty() + "</td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Fuel Filter:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getFuelFilter() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getFuelFilterQty() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Oil Filter:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getOilFilter() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getOilFilterQty() + "</td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Air Filter:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getAirfiler() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getAirfilterQty() + "</td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr >" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Time In:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getTimeIn() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Time Out:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getTimeOut() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Date:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getDate() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Additional remark:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getRemark() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Technician(s):</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getTechnician() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + serviceInspectionDto.getTechnician2() + "</td>" +
                "</tr>" +
                "</table>" +

                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">For Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:12pt; color: Red;\">This report was generated runtime from the field</p>" +
                "<p  style=\"font-family: verdana; font-size:11pt;font-style:italic\">user details:</p>" +
                "<ul>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Username: " + serviceInspectionDto.getTechnician() + "</li>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Powergen Customer Experience Center</li>" +
                "</ul>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: Powergenltd.com</p>";
        return message;
    }
    public String breakdownReportNotification(BreakdownReportDto breakdownReportDto){
        final String message = "<style>" +
                "</style>" +
                "Please find below, details of Breakdown <br>" +
                "<Table class=\"table-striped\" style:  \"border-spacing: 0;" +
                "  border-collapse: collapse; border: 1px solid #ddd !important;\">" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Customer name:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getFirstName() + "</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Location:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getStreetAdd() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Engine Serial:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getEngineSerial() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Alternator type:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getAlternatorSerial() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Gen capacity:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getGeneratorSize() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Hour Reading:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getHourReading() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Next Service Hour:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getNextServiceHour() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Breakdown Description:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getBreakdownDescription() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Root Cause:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getRootCause() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Previous action taken:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getPreviousAction() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Action Taken:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getPreviousAction() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Action Points:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getActionTaken() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Recommendation</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getRecommendation() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Require Followup/observation:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getIsfollowUp() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Can This gen be in use:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getGenISUsable() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr >" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Time In:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getTimeIn() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Time Out:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getTimeOut() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Date:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getDate() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Additional remark:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getComment() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Technician:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + breakdownReportDto.getTechnician() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "</table>" +

                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">For Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:12pt; color: Red;\">This report was generated runtime from the field</p>" +
                "<p  style=\"font-family: verdana; font-size:11pt;font-style:italic\">user details:</p>" +
                "<ul>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Username: user</li>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Role: powergen customer experience center</li>" +
                "</ul>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: Powergenltd.com</p>";
        return message;
    }
    public String InspectionReportNotification(GeneratorInspectionDto generatorInspectionDto){
        final String message = "<style>" +
                "</style>" +
                "Please find below, details of of Field Inspection<br>" +
                "<Table class=\"table-striped\" style:  \"border-spacing: 0;" +
                "  border-collapse: collapse; border: 1px solid #ddd !important;\">" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Customer name:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getClientName() + " " + "</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Generator Location:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getGenAddress() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + "</td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Engine Serial:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getEngineSerialNumber() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Alternator type:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getAlternatorNum() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Gen capacity:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getGeneratorSize() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Hour Reading:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getHour_Reading() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Next Service Hour:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getNext_service() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Total number of services on generator:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getService_count() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">General Checks:</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Verify Fuel Leakages:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getVerifyFuelLeakage() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Fluid Levels(oil, coolant):</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getFluidLevel() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Check Fan Belts:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getFanbeltCheck() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Check Oil leakages:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getOilLeakage() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr></tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Electrical Checks</td>" +
                "</tr>" +

                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Verify Correct Ac and Dc connection:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getAcAndDcConnection() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Battery and Battery Terminal Check:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getBatteryCheck() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr></tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Charging Alternator Check:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getChargingAlternatoreCheck() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">KickStarter Check:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getKickStarterCheck() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr></tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Running Checks:</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Test on Load:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getTestONload() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Frequency Check:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getFrequencyCheck() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Coolant and Engine Temperature</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getTemperature() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Oil Pressure</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getOilPressure() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr></tr>" +
                "<tr >" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Time In:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getTimeIn() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Time Out:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getTimeOut() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Date:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getDate() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Observations and Conclusion</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getRemark() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Technician:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getTechnician() + "</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + generatorInspectionDto.getTechnician2() + "</td>" +
                "</tr>" +
                "</table>" +
                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">For Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:12pt; color: Red;\">This report was generated from the field</p>" +
                "<p  style=\"font-family: verdana; font-size:11pt;font-style:italic\">user details:</p>" +
                "<ul>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Username: " + generatorInspectionDto.getTechnician() + "</li>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\"> powergen customer experience center</li>" +
                "</ul>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: Powergenltd.com</p>";
        return message;
    }
    public String[] prospectiveClientNNotification(MarkettingDto markettingDto)
    {
        String message = "<style>" +
                "</style>" +
                "Field Marketting<br>" +
                "<Table class=\"table-striped\" style:  \"border-spacing: 0;" +
                "  border-collapse: collapse; border: 1px solid #ddd !important;\">" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<th style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></th>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Customer:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + "Company name: " + markettingDto.getCompanyname() + "</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + "Contact Person: " + markettingDto.getContactname() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Email Address:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getEmail() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Phone Number:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getPhone() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Address:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getAddress() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Order Category:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getInterest() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Power Requirement:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getPowerRequirement() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr style=\"; background-color: #f9f9f9\">" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Recommendation:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getRecommendation() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">Additional Information:</td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\">" + markettingDto.getAdditionalInformation() + " </td>" +
                "<td style=\"padding: 8px;\n" +
                "  line-height: 1.42857143;\n" +
                "  vertical-align: top;\n" +
                "  border-top: 1px solid #ddd\"></td>" +
                "</tr>" +

                "</table>" +
                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">For Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:12pt; color: Red;\">This report was generated runtime from the field</p>" +
                "<p  style=\"font-family: verdana; font-size:11pt;font-style:italic\">user details:</p>" +
                "<ul>" +
                "<li style=\"font-family: verdana; font-size:10pt;font-style:italic\">Sales Officer: " + markettingDto.getSalesPerson() + "</li>" +
                "</ul>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: http://powergenltd.com</p>";
        String message2 = "Dear "+markettingDto.getContactname()+",<br>" +
                "Thank you for sparing out time to attend to us<br>" +
                "A representative from Powergen will contact you shortly to follow up on your interest in : " + markettingDto.getInterest() + "<br><br>" +
                "Our Products ranges from <ul><li>Generators: 13MVA to 2.5MVA</li>" +
                "<li>Inverters: 1.5kva to 30kva</li>" +
                "<li>Solar Panels</li>" +
                "<li>Ats Panels. etc</li>" +
                "</ul>" +
                "Learn more about our products and services by visiting our website <a href=\"http:www.powergenltd.com\">www.powergenltd.com</a></br>" +
                "<p style=\"font-family: times, serif; font-size:10pt;\">Kind Regards</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Powergen Engineering Limited</p><br>" +
                "<p  style=\"font-family: verdana; font-size:14pt;font-style:italic\">Powergen Customer Experience Center</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Email: Powergencustomercare@gmail.com</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Mobile: 0700-77777-17, 0700-77777-07" +
                "<p  style=\"font-family: 'Comic Sans MS'; font-size:14pt; color: Red;\">This is an automatic mail service</p>" +
                "<p  style=\"font-family: verdana; font-size:10pt;\">Website: Powergenltd.com</p>";
        String[] returnMessage = {message,message2};
        return returnMessage;
    }
}
