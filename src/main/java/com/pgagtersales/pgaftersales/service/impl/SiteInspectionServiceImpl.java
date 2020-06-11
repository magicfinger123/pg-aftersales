/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.SiteInspectionEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.SiteInspectionRepository;
import com.pgagtersales.pgaftersales.service.SiteInspectionService;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SiteInspectionServiceImpl implements SiteInspectionService {
    @Autowired
    SiteInspectionRepository siteInspectionRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    SendMail sendMail;
    @Autowired
    NotificationMessages message;

    String[] recipent = {"exc.easey@gmail.com"};
    String[] ccRecipent = {"ossaimike8@gmail.com","exc.easey@gmail.com"};

    @SneakyThrows
    @Override
    public ApiResponse addSiteInspection(SiteInspectionDto siteInspectionDto, Boolean sendNotification) {
       // SiteInspectionEntity inspectionEntity = new SiteInspectionEntity();
       // BeanUtils.copyProperties(siteInspectionDto, inspectionEntity);
        //SiteInspectionEntity saveUser = siteInspectionRepository.save(inspectionEntity);
        ApiResponse apiResponse = responseBuilder.successfullResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Site Inspection Logged Successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        if (sendNotification){
            sendMail.sendEmailWithAttachment(message.siteInspectionNotification(siteInspectionDto),recipent,ccRecipent, "POWERGEN SITE INSPECTION REPORT");
        }
        return apiResponse;
    }
}
