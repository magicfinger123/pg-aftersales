/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.MarkettingEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.MarkettingRepository;
import com.pgagtersales.pgaftersales.service.MarkettingService;
import com.pgagtersales.pgaftersales.shared.dto.MarkettingDto;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MarkettingServiceImpl implements MarkettingService {

    @Autowired
    MarkettingRepository markettingRepository;
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
    public ApiResponse addNewMarkettingLead(MarkettingDto markettingDto) {
        ModelMapper modelMapper = new ModelMapper();
        MarkettingEntity markettingEntity = modelMapper.map(markettingDto, MarkettingEntity.class);
        MarkettingEntity saveEntity = markettingRepository.save(markettingEntity);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Prospective Client detail has been logged").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        sendMail.sendEmailWithAttachment(message.prospectiveClientNNotification(markettingDto)[0],recipent,ccRecipent, "POWERGEN SITE INSPECTION REPORT");
        sendMail.sendEmailWithAttachment(message.prospectiveClientNNotification(markettingDto)[1], new String[]{markettingDto.getEmail()},ccRecipent, "POWERGEN SITE INSPECTION REPORT");
        return apiResponse;
    }
}
