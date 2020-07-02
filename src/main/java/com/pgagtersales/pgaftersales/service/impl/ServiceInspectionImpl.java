/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ServiceInspectionEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ServiceInspectionRepository;
import com.pgagtersales.pgaftersales.service.ServiceInspectionService;
import com.pgagtersales.pgaftersales.shared.dto.ServiceInspectionDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceInspectionImpl implements ServiceInspectionService {
    @Autowired
    ServiceInspectionRepository serviceInspectionRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    SendMail sendMail;
    @Autowired
    NotificationMessages message;
    String[] recipent = {"powergenltd@gmail.com"};
    String[] ccRecipent = {"info@powergen@gmail.com"};
    @SneakyThrows
    @Override
    public ApiResponse addServiceInspection(ServiceInspectionDto serviceInspectionDto, Boolean sendNotification) throws Exception{
        ServiceInspectionEntity serviceInspectionEntity = new ServiceInspectionEntity();
        BeanUtils.copyProperties(serviceInspectionDto, serviceInspectionEntity);
        System.out.println("servicereport "+serviceInspectionEntity);
        ServiceInspectionEntity saveReport = serviceInspectionRepository.save(serviceInspectionEntity);
        if (saveReport == null){
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Could not save report")
                    .developerMessage("something went wrong")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        }
        else {
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Service/Inspection Logged Successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            if (sendNotification) {
                sendMail.sendEmailWithAttachment(message.serviceReportNotification(serviceInspectionDto), recipent, ccRecipent,"GENERATOR SERVICE NOTIFICATION");
            }
            return apiResponse;
        }
    }
}
