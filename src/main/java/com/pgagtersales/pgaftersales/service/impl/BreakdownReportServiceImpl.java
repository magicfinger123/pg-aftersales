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
import com.pgagtersales.pgaftersales.io.entity.BreakdownReportEntity;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.BreakdownReportRepository;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.BreakdownReportService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.BreakdownReportDto;
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
public class BreakdownReportServiceImpl implements BreakdownReportService {
    @Autowired
    BreakdownReportRepository breakdownReportRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    SendMail sendMail;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    NotificationMessages message;
    String[] recipent = {"mikeossaiofficial@gmail.com"};

    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    ReportLogRepo reportLogRepo;

    @SneakyThrows
    @Override
    public ApiResponse addBreakdownReport(BreakdownReportDto breakdownReportDto, Boolean sendNotification) {
        BreakdownReportEntity breakdownReportEntity = new BreakdownReportEntity();
        BeanUtils.copyProperties(breakdownReportDto, breakdownReportEntity);
        BreakdownReportEntity saveReport = breakdownReportRepository.save(breakdownReportEntity);
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
            String new_recipent = utils.getEmails("default").getEmailAddress();
                    //breakdownReportDto.getClient_email();
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Breakdown report Logged Successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            if (sendNotification) {
                sendMail.sendEmailWithAttachment(message.breakdownReportNotification(breakdownReportDto), new_recipent, AppConstants.AFTERSALES_RECIPENTS, "GENERATOR BREAKDOWN NOTIFICATION");
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getPrincipal().toString();
            System.out.println(username);
            UserEntity userDto = userRepository.findByUsername(username);
            ClientsEntity client = clientRepository.findById(Integer.parseInt(breakdownReportDto.getClient_id()));
            try {
                ReportLogDto reportLogDto = new ReportLogDto();
                reportLogDto.setUserId(userDto.getUserId());
                reportLogDto.setDescription("Attended to breakdown \n"+client.getCompany());
                reportLogDto.setAction("Breakdown");
                reportLogDto.setStatus(breakdownReportDto.getRecommendation());
                reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
                reportLogDto.setTime(utils.getTime());
                ReportLogEntity ent = new ReportLogEntity();
                BeanUtils.copyProperties(reportLogDto,ent);
                reportLogRepo.save(ent);
            } catch (BeansException e) {
                System.out.println("reportlog error: "+e.getLocalizedMessage());
            }
            notificationRequestDto.setTarget("admin");
            notificationRequestDto.setTitle("PEL");
            notificationRequestDto.setBody("A breakdown was attended to by "+message.getUserDetails().getFirst_name()+" check your mail for details");
            notificationController.sendPnsToTopic(notificationRequestDto);

            return apiResponse;
        }
    }
}
