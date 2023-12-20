/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.*;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ActionResponse;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.GeneratorRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.SlaPriceListRepository;
import com.pgagtersales.pgaftersales.repository.SlaRepository;
import com.pgagtersales.pgaftersales.service.SlaService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.*;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SlaServiceImpl implements SlaService {
    @Autowired
    SlaRepository slaRepository;
    @Autowired
    SlaPriceListRepository slaPriceListRepository;
    @Autowired
    GeneratorRepository generatorRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    private SendMail sendMail;

    @Autowired
    NotificationMessages message;

    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
    @Autowired
    private ReportLogRepo reportLogRepo;
    @Autowired
    private Utils utils;


    @Override
    public ApiResponse updateGenService(SlaEntity slaEntity) {
        if (slaEntity==null){
            throw new UserServiceException("sla is null","sla is null");
        }
        System.out.println("sla entity: "+slaEntity);
        SlaEntity slaEntity1 = slaRepository.save(slaEntity);
        if (slaEntity1 == null){
            throw new UserServiceException("something went wrong","Sla returned null");
        }else{
            ActionResponse actionResponse = ActionResponse.builder()
                    .actionStatus("Successful")
                    .action("Updated Generator Service Details")
                    .build();
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(actionResponse);
            return apiResponse;
        }
    }

    @SneakyThrows
    @Override
    public ApiResponse notifyCustomer(SlaRequestDto slaRequestDto) {
        SlaPriceListEntity priceList = slaPriceListRepository.findByRating(slaRequestDto.getGenCategory());
        GeneratorEntity gen = generatorRepository.findById(Integer.parseInt(slaRequestDto.getGenId()));
        if (gen != null){
            slaRequestDto.setGenLocation(gen.getLocation());
        }
        if (priceList == null){
            throw new UserServiceException("Wrong input","genCategory not in priceList"+slaRequestDto.getGenCategory());
        }
        switch (slaRequestDto.getSlaType()){
            case "sixService":
                slaRequestDto.setAmount(priceList.getSixService());
                slaRequestDto.setSlaType("6 services");
                break;
            case "eightService":
                slaRequestDto.setAmount(priceList.getEightService());
                slaRequestDto.setSlaType("8 services");
                break;
            case "tenService":
                slaRequestDto.setAmount(priceList.getTenService());
                slaRequestDto.setSlaType("10 services");
                break;
            case "twelveService":
                slaRequestDto.setAmount(priceList.getTwelveService());
                slaRequestDto.setSlaType("12 services");
                break;
        }
        String new_recipennt = slaRequestDto.getClient_email();
        if(!slaRequestDto.getRenewal()){
            sendMail.sendEmailWithAttachment(message.slaProposal(slaRequestDto), new_recipennt, AppConstants.AFTERSALES_RECIPENTS, "GENERATOR MAINTENANCE PROPOSAL");
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Generator Maintenance proposal sent successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            notificationRequestDto.setTarget("admin");
            notificationRequestDto.setTitle("PEL SLA PROPOSAL");
            notificationRequestDto.setBody("proposal sent by "+message.getUserDetails().getFirst_name()+" to "+slaRequestDto.getClientName()+" on "+slaRequestDto.getGenSize());
            notificationController.sendPnsToTopic(notificationRequestDto);
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(message.getUserDetails().getUserId());
            reportLogDto.setDescription("Sent maintenance proposal to "+slaRequestDto.getClientName()+" on his/her "+slaRequestDto.getGenSize());
            reportLogDto.setAction("Maintenance proposal");
            reportLogDto.setStatus("completed");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
            return apiResponse;
        }else {
            sendMail.sendEmailWithAttachment(message.slaRequestotification(slaRequestDto), new_recipennt, AppConstants.AFTERSALES_RECIPENTS, "GENERATOR MAINTENANCE RENEWAL NOTICE");
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            SuccessMessage successMessage = SuccessMessage.builder().message("Renewal Notice sent successfully").build();
            apiResponse.responseEntity = ResponseEntity.ok(successMessage);
            notificationRequestDto.setTarget("admin");
            notificationRequestDto.setTitle("PEL SLA RENEWAL");
            notificationRequestDto.setBody("renewal notice sent by "+message.getUserDetails().getFirst_name()+" to "+slaRequestDto.getClientName()+" on "+slaRequestDto.getGenSize());
            notificationController.sendPnsToTopic(notificationRequestDto);
            try {
                ReportLogDto reportLogDto = new ReportLogDto();
                reportLogDto.setUserId(message.getUserDetails().getUserId());
                reportLogDto.setDescription("Sent Sla renewal to "+slaRequestDto.getClientName()+" on his/her "+slaRequestDto.getGenSize());
                reportLogDto.setAction("SLA renewal notice");
                reportLogDto.setStatus("completed");
                reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
                reportLogDto.setTime(utils.getTime());
                ReportLogEntity ent = new ReportLogEntity();
                BeanUtils.copyProperties(reportLogDto,ent);
                reportLogRepo.save(ent);
            } catch (BeansException e) {
                e.printStackTrace();
            }
            return apiResponse;
        }

    }
    @Override
    public ApiResponse updatePriceList(SlaPriceListDto slaPriceListDto) {
        SlaPriceListEntity slaPriceListEntity = new SlaPriceListEntity();
        SlaPriceListEntity priceList;
        SlaPriceListEntity existing = slaPriceListRepository.findByRating(slaPriceListDto.getRating());
        if (existing != null){
            BeanUtils.copyProperties(slaPriceListDto,existing);
             priceList = slaPriceListRepository.save(existing);
            try {
                ReportLogDto reportLogDto = new ReportLogDto();
                reportLogDto.setUserId(message.getUserDetails().getUserId());
                reportLogDto.setDescription("Updated Sla maintenance price for "+slaPriceListDto.getRating()+ "Generator");
                reportLogDto.setAction("Updated maintenance contract price");
                reportLogDto.setStatus("completed");
                reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
                reportLogDto.setTime(utils.getTime());
                ReportLogEntity ent = new ReportLogEntity();
                BeanUtils.copyProperties(reportLogDto,ent);
                reportLogRepo.save(ent);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }else{
            BeanUtils.copyProperties(slaPriceListDto, slaPriceListEntity);
             priceList = slaPriceListRepository.save(slaPriceListEntity);
            try {
                ReportLogDto reportLogDto = new ReportLogDto();
                reportLogDto.setUserId(message.getUserDetails().getUserId());
                reportLogDto.setDescription("Added Sla maintenance price for "+slaPriceListDto.getRating()+ "Generator");
                reportLogDto.setAction("Added maintenance contract price");
                reportLogDto.setStatus("completed");
                reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
                reportLogDto.setTime(utils.getTime());
                ReportLogEntity ent = new ReportLogEntity();
                BeanUtils.copyProperties(reportLogDto,ent);
                reportLogRepo.save(ent);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
        SlaPriceListDto returnValue = new SlaPriceListDto();
        BeanUtils.copyProperties(priceList,returnValue);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }
    @Override
    public ApiResponse getAllSla(int page, int size) {
        List<SlaPriceListDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<SlaPriceListEntity> slaEntities = slaPriceListRepository.findAll(pageable);
        List<SlaPriceListEntity> slaContent = slaEntities.getContent();
        for (SlaPriceListEntity ent : slaContent) {
            SlaPriceListDto dto = new SlaPriceListDto();
            BeanUtils.copyProperties(ent, dto);
            returnValue.add(dto);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }
}

