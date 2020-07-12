/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

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
import com.pgagtersales.pgaftersales.service.ServiceInspectionService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.ServiceInspectionDto;
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
public class ServiceInspectionImpl implements ServiceInspectionService {
    @Autowired
    ServiceInspectionRepository serviceInspectionRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    SendMail sendMail;
    @Autowired
    NotificationMessages message;
  /*  String[] recipent = {"ossaimike8@gmail.com"};
    String[] ccRecipent = {"exc.easey@gmail.com"};*/
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
            String new_recipennt = serviceInspectionDto.getClient_email();
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Service/Inspection Logged Successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            if (sendNotification) {
                sendMail.sendEmailWithAttachment(message.serviceReportNotification(serviceInspectionDto), new_recipennt, AppConstants.AFTERSALES_RECIPENTS, "GENERATOR SERVICE NOTIFICATION");
            }
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String username = auth.getPrincipal().toString();
                System.out.println(username);
                UserEntity userDto = userRepository.findByUsername(username);
                ClientsEntity client = clientRepository.findById(Integer.parseInt(serviceInspectionDto.getClient_id()));
            try {
                ReportLogDto reportLogDto = new ReportLogDto();
                reportLogDto.setUserId(userDto.getUserId());
                reportLogDto.setDescription("Generator Servicing  at \n"+client.getCompany());
                reportLogDto.setAction("Generator Servicing");
                reportLogDto.setStatus("completed");
                reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
                reportLogDto.setTime(utils.getTime());
                ReportLogEntity ent = new ReportLogEntity();
                BeanUtils.copyProperties(reportLogDto,ent);
                reportLogRepo.save(ent);
            } catch (BeansException e) {
                System.out.println("Report log error "+e.getMessage());
            }
            return apiResponse;
        }
    }
}
