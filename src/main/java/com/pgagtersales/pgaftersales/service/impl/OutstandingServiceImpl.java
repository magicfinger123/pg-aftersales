/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.io.entity.OutstandingEntity;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.ClientDtoReq;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.OutstandingRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.service.OutstandingService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutstandingServiceImpl implements OutstandingService {
    @Autowired
    OutstandingRepository outstandingRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SendMail sendMail;

    @Autowired
    NotificationMessages message;

    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();

    @Autowired
    ReportLogRepo reportLogRepo;

    @Autowired
    private Utils utils;


    @Override
    public ApiResponse getOutstanding(int page, int size) {
        List<OutstandingDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<OutstandingEntity> outstanding = outstandingRepository.findAll(pageable);
        if (outstanding.isEmpty()) {
            throw new UserServiceException("Something went wrong","outstanding returned null");
        } else {
            List<OutstandingEntity> outstandingEntities = outstanding.getContent();
            for (OutstandingEntity out : outstandingEntities) {
                OutstandingDto outDto = new OutstandingDto();
                BeanUtils.copyProperties(out, outDto);
                returnValue.add(outDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse searchOutstandingByClient(String alias, int page, int size) {
        return null;
    }

    @Override
    public ApiResponse addOutstanding(OutstandingDto outstandingDto) {
        String recipent = utils.getEmails("default").getEmailAddress();
        ModelMapper modelMapper = new ModelMapper();
        ClientsEntity client = clientRepository.findById(Integer.parseInt(outstandingDto.getClientId()));
        if (client == null){
            throw new UserServiceException("associated client not found: "+outstandingDto.getClientId(),"associated client not found");
        }
        ClientDto clientDto = modelMapper.map(client,ClientDto.class);
        outstandingDto.setDate(utils.getDate());
        outstandingDto.setUser(message.getUserDetails().getFirst_name());
        clientDto.getOutstandingDtos().add(outstandingDto);
        ClientsEntity updateEntity = modelMapper.map(clientDto,ClientsEntity.class);
        updateEntity.setPassword(client.getPassword());

        ClientsEntity savedClientEntity = clientRepository.save(updateEntity);
        if (savedClientEntity == null){
            throw new UserServiceException("unable to add generator","unable to add generator");
        }
        ClientDto  returnValue =  modelMapper.map(savedClientEntity, ClientDto.class);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);

        try {
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(message.getUserDetails().getUserId());
            reportLogDto.setDescription("Invoice was created \n"+client.getCompany());
            reportLogDto.setAction("Invoice created");
            reportLogDto.setStatus("completed");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
        } catch (BeansException e) {
            System.out.println("report log error"+e.getLocalizedMessage());
        }
        notificationRequestDto.setTarget("admin");
        notificationRequestDto.setTitle("PEL");
        notificationRequestDto.setBody("Invoice was done by "+message.getUserDetails().getFirst_name()+" on "+client.getCompany());
        notificationController.sendPnsToTopic(notificationRequestDto);
        return apiResponse;
    }

    @Override
    public ApiResponse updateOutstanding(int id, OutstandingDto outstandingDto) {
        OutstandingDto returnValue = new OutstandingDto();
        OutstandingEntity entity = outstandingRepository.findById(id);

        if (entity == null) {
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Error Occured")
                    .developerMessage("Id not found")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            String date = entity.getDate();
            BeanUtils.copyProperties(outstandingDto, entity);
            entity.setId(id);
            entity.setDate(date);
            entity.setUser(message.getUserDetails().getUserId());
            OutstandingEntity saveOutstanding = outstandingRepository.save(entity);
            BeanUtils.copyProperties(saveOutstanding, returnValue);
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }


    @Override
    public ApiResponse deleteOutstanding(String clientId) {
        return null;
    }
}
