/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.MarkettingEntity;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.MarkettingRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.MarkettingService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.MarkettingDto;
import com.pgagtersales.pgaftersales.shared.dto.NotificationRequestDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
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
public class MarkettingServiceImpl implements MarkettingService {

    @Autowired
    MarkettingRepository markettingRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    SendMail sendMail;
    @Autowired
    NotificationMessages message;
  /*  String[] ccRecipent = {"exc.easey@gmail.com"};*/
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportLogRepo reportLogRepo;
    @Autowired
    private Utils utils;

    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();

    @SneakyThrows
    @Override
    public ApiResponse addNewMarkettingLead(MarkettingDto markettingDto) {
        String recipent = utils.getEmails("default").getEmailAddress();
        ModelMapper modelMapper = new ModelMapper();
        MarkettingEntity markettingEntity = modelMapper.map(markettingDto, MarkettingEntity.class);
        MarkettingEntity saveEntity = markettingRepository.save(markettingEntity);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getPrincipal().toString();
        System.out.println(username);
        UserEntity userDto = userRepository.findByUsername(username);
        //ClientsEntity client = clientRepository.findById(Integer.parseInt(siteInspectionDto.clientId));
        try {
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(userDto.getUserId());
            reportLogDto.setDescription("Field Marketing activity @"+markettingDto.getAddress()+
                    " meet (Mr/Mrs)"+markettingDto.getContactname()+"who might be interested in "+markettingDto.getInterest());
            reportLogDto.setStatus("completed");
            reportLogDto.setAction("Field Marketing");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
        } catch (BeansException e) {
            System.out.println("report log error"+e.getMessage());
        }
        notificationRequestDto.setTarget("admin");
        notificationRequestDto.setTitle("PEL");
        notificationRequestDto.setBody("Field Marketing report submitted by "+message.getUserDetails().getFirst_name());
        notificationController.sendPnsToTopic(notificationRequestDto);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Prospective Client detail has been logged").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        sendMail.sendEmailWithAttachment(message.prospectiveClientNNotification(markettingDto)[0],recipent, AppConstants.SALES_RECIPENT, "FIELD MARKETING ACTIVITY");
        sendMail.sendEmailWithAttachment(message.prospectiveClientNNotification(markettingDto)[1], markettingDto.getEmail(),AppConstants.SALES_RECIPENT, "POWERGEN ENGINEERING LIMITED");

        return apiResponse;
    }
}
