/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.service.VehicleInspectionService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.VehicleInspectionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class VehicleInspectionServiceImpl implements VehicleInspectionService {
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    private SendMail sendMail;
    @Autowired
    NotificationMessages message;
    @Autowired
    ReportLogRepo reportLogRepo;
    @Autowired
    private Utils utils;
    @Override
    public ApiResponse vehicleInspectionReport(VehicleInspectionDto vehicleInspection) {
        String new_recipennt =  utils.getEmails("default").getEmailAddress();
        try {
            sendMail.sendEmailWithAttachment(message.vehicleReport(vehicleInspection), new_recipennt, AppConstants.HR_RECIPENTS, "VEHICLE INSPECTION NOTIFICATION");
        } catch (MessagingException e) {
            System.out.println("message_exception: "+e);
            throw new UserServiceException("Something went wrong",e.getMessage());
        } catch (IOException e) {
            System.out.println("message_exception: "+e);
            throw new UserServiceException("Something went wrong",e.getMessage());
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Inspection report successfully sent").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        try {
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(message.getUserDetails().getUserId());
            reportLogDto.setDescription("Vehicle inspection carried out on ");
            reportLogDto.setAction("Vehicle Inspection");
            reportLogDto.setStatus("Vehicle Inspection");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }
}
