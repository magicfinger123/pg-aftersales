/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.entity.ServiceInspectionEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.ServiceInspectionRepository;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.GeneratorInspectionService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorInspectionDto;
import com.pgagtersales.pgaftersales.shared.dto.NotificationRequestDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
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
public class GeneratorInspectionServiceImpl implements GeneratorInspectionService {
    @Autowired
    ServiceInspectionRepository serviceInspectionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    SendMail sendMail;
    @Autowired
    NotificationMessages message;
    /*String[] recipent = {"ossaimike8@gmail.com"};
    String[] ccRecipent = {"mikeossaiofficial@gmail.com"};*/
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportLogRepo reportLogRepo;

    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();

    @Autowired
    private Utils utils;

    @SneakyThrows
    @Override
    public ApiResponse addInspection(GeneratorInspectionDto generatorInspectionDto, Boolean sendNotification) {
        ServiceInspectionEntity serviceInspectionEntity = new ServiceInspectionEntity();
        generatorInspectionDto.setStatus("Inspection");
        BeanUtils.copyProperties(generatorInspectionDto, serviceInspectionEntity);
        System.out.println("InspectionReport "+serviceInspectionEntity);
        ServiceInspectionEntity saveReport = serviceInspectionRepository.save(serviceInspectionEntity);
        if (saveReport == null){
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Could not save report")
                    .developerMessage("something went wrong")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            String new_recipennt = generatorInspectionDto.getClient_email();
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Inspection Report Logged Successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            if (sendNotification) {
                sendMail.sendEmailWithAttachment(message.InspectionReportNotification(generatorInspectionDto), new_recipennt, AppConstants.AFTERSALES_RECIPENTS,"GENERATOR INSPECTION NOTIFICATION");
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getPrincipal().toString();
            System.out.println(username);
            UserEntity userDto = userRepository.findByUsername(username);
            ClientsEntity client = clientRepository.findById(Integer.parseInt(generatorInspectionDto.getClient_id()));
            ReportLogDto reportLogDto = new ReportLogDto();
            try {
                reportLogDto.setUserId(userDto.getUserId());
                reportLogDto.setDescription("Generator inspection at \n"+client.getCompany());
                reportLogDto.setAction("Generator Inspection");
                reportLogDto.setStatus("completed");
                reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
                reportLogDto.setTime(utils.getTime());
                ReportLogEntity ent = new ReportLogEntity();
                BeanUtils.copyProperties(reportLogDto,ent);
                reportLogRepo.save(ent);
            } catch (BeansException e) {
                System.out.println("ReportLog error"+e.getLocalizedMessage());
            }
            notificationRequestDto.setTarget("admin");
            notificationRequestDto.setTitle("PEL");
            notificationRequestDto.setBody("An inspection was done by "+message.getUserDetails().getFirst_name()+" on "+client.getCompany()+" Generators");
            notificationController.sendPnsToTopic(notificationRequestDto);

            return apiResponse;
        }
    }
}

