/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.BreakdownReportEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.BreakdownReportRepository;
import com.pgagtersales.pgaftersales.service.BreakdownReportService;
import com.pgagtersales.pgaftersales.shared.dto.BreakdownReportDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BreakdownReportServiceImpl implements BreakdownReportService {
    @Autowired
    BreakdownReportRepository breakdownReportRepository;
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
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Breakdown report Logged Successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            if (sendNotification) {
                sendMail.sendEmailWithAttachment(message.breakdownReportNotification(breakdownReportDto), recipent, ccRecipent, "GENERATOR BREAKDOWN NOTIFICATION");
            }
            return apiResponse;
        }
    }
    }
