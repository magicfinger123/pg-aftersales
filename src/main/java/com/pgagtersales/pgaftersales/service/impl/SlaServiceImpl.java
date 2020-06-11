/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.SlaEntity;
import com.pgagtersales.pgaftersales.model.response.ActionResponse;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.SlaRepository;
import com.pgagtersales.pgaftersales.service.SlaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class SlaServiceImpl implements SlaService {
    @Autowired
    SlaRepository slaRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Override
    public ApiResponse updateGenService(SlaEntity slaEntity) {
        if (slaEntity==null){
            throw new UserServiceException("sla is null","sla is null");
        }
        System.out.println("sla entity: "+slaEntity);
        SlaEntity slaEntity1 = slaRepository.save(slaEntity);
        if (slaEntity1 == null){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .developerMessage("failed to update generator service Status")
                    .userMessage("something was not right")
                    .build();
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        }else{
            ActionResponse actionResponse = ActionResponse.builder()
                    .actionStatus("Successful")
                    .action("Update Generator Service Details")
                    .build();
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(actionResponse);
            return apiResponse;
        }
    }
 /*   public boolean checknull(SlaEntity slaEntity) throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
            return true;
        }*/
    }

