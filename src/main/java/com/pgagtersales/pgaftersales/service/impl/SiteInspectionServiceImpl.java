/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.SiteInspectionRepository;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.SiteInspectionService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


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
   /* String[] ccRecipent = {"ossaimike8@gmail.com"};*/
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReportLogRepo reportLogRepo;
    @Autowired
    private Utils utils;

    @SneakyThrows
    @Override
    public ApiResponse addSiteInspection(SiteInspectionDto siteInspectionDto, Boolean sendNotification) {
       // SiteInspectionEntity inspectionEntity = new SiteInspectionEntity();
       // BeanUtils.copyProperties(siteInspectionDto, inspectionEntity);
        //SiteInspectionEntity saveUser = siteInspectionRepository.save(inspectionEntity);
        String new_recipent = siteInspectionDto.getClientEmail();
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Site Inspection Logged Successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getPrincipal().toString();
        System.out.println(username);
        UserEntity userDto = userRepository.findByUsername(username);
        ClientsEntity client = clientRepository.findById(Integer.parseInt(siteInspectionDto.clientId));
        try {
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(message.getUserDetails().getUserId());
            reportLogDto.setDescription("Site inspection at \n"+client.getCompany());
            reportLogDto.setAction("Site Inspection");
            reportLogDto.setStatus("completed");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
        } catch (BeansException e) {
            System.out.println("report log error"+e.getMessage());
        }
        if (sendNotification){
            sendMail.sendEmailWithAttachment(message.siteInspectionNotification(siteInspectionDto),new_recipent, AppConstants.AFTERSALES_RECIPENTS, "POWERGEN SITE INSPECTION REPORT");
        }
        return apiResponse;
    }
}
