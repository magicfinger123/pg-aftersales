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
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.ReportLogService;
import com.pgagtersales.pgaftersales.service.UserService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportSubmissionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportLogServiceImpl implements ReportLogService {
    @Autowired
    ReportLogRepo reportLogRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ResponseBuilder responseBuilder;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private NotificationMessages message;

    @Autowired
    private Utils utils;


    String[] ccrecipent = {"powergenltd@gmail.com"};

    @Override
    public ApiResponse getUserReportByDate(String alias1, String alias2, int page, int size) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<ReportLogEntity> reportPage = reportLogRepo.findByUserByDate(alias1, alias2, pageable);
        /*if (reportPage.isEmpty()){
            throw new UserServiceException("no report found","no report found");
        }*/
        List<ReportLogEntity> reports = reportPage.getContent();

        List<ReportLogDto> reportLogDtos = new ArrayList<>();
        for (ReportLogEntity log:reports) {
            ReportLogDto logDto = new ReportLogDto();
            BeanUtils.copyProperties(log,logDto);
            reportLogDtos.add(logDto);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(reportLogDtos);
        return apiResponse;
    }


    @Override
    public ApiResponse getAllReports() {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<ReportLogEntity> reportPage = reportLogRepo.findAll(pageable);
        List<ReportLogEntity> reports = reportPage.getContent();
        List<ReportLogDto> reportLogDtos = new ArrayList<>();
        for (ReportLogEntity log:reports) {
            ReportLogDto logDto = new ReportLogDto();
            BeanUtils.copyProperties(log,logDto);
            reportLogDtos.add(logDto);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(reportLogDtos);
        return apiResponse;
    }

    @Override
    public ApiResponse addLog(ReportLogDto reportLogDto) {
        ReportLogEntity reportLogEntity = new ReportLogEntity();
       if( userRepository.findByUserId(reportLogDto.getUserId())== null){
           throw new UserServiceException("user not found","user with id "+reportLogDto.getUserId()+" not found");
       }
       BeanUtils.copyProperties(reportLogDto,reportLogEntity);
       if(reportLogRepo.save(reportLogEntity)==null){
           throw new UserServiceException("unable to save","unable to save log");
       }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Report logged successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }

    @Override
    public ApiResponse submitReport(ReportSubmissionDto reportSubmissionDto) {
        String recipent = utils.getEmails("default").getEmailAddress();
        UserEntity user = userRepository.findByUserId(reportSubmissionDto.getUserId());
        if( user == null){
            throw new UserServiceException("user not found","user with id "+reportSubmissionDto.getUserId()+" not found");
        }
        reportSubmissionDto.setDepartment(user.getDepartment());
        reportSubmissionDto.setFullname(user.getFirst_name()+" "+user.getLast_name());
        try {
            sendMail.sendEmailWithAttachment(message.reportsSubmit(reportSubmissionDto),recipent, AppConstants.HR_RECIPENTS, "Staff Daily Report");
        } catch (MessagingException e) {
            throw new UserServiceException("unable to send report","something went wrong "+e);
        } catch (IOException e) {
            throw new UserServiceException("unable to send report","something went wrong "+e);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Report sent successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
        /* if (reportSubmissionDto.getReportLogDtos().size() < 1) {
            ApiResponse response = getUserReportByDate(reportSubmissionDto.getUserId(), reportSubmissionDto.getDate(), 0, Integer.MAX_VALUE);
            return response;
        }*/
    }
}
