/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.io.entity.OutstandingEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.ClientDto;
import com.pgagtersales.pgaftersales.repository.OutstandingRepository;
import com.pgagtersales.pgaftersales.service.OutstandingService;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.OutstandingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutstandingServiceImpl implements OutstandingService {
    @Autowired
    OutstandingRepository outstandingRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Override
    public ApiResponse getOutstanding(int page, int size) {
        List<OutstandingDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<OutstandingEntity> outstanding = outstandingRepository.findAll(pageable);
        if (outstanding == null || outstanding.isEmpty()) {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .userMessage("No result")
                    .developerMessage("getGenerators returned null value")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            List<OutstandingEntity> outstandingEntities = outstanding.getContent();
            for (OutstandingEntity out : outstandingEntities) {
                OutstandingDto outDto = new OutstandingDto();
                BeanUtils.copyProperties(out, outDto);
                returnValue.add(outDto);
            }
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse searchOutstandingbyClient(String alias, int page, int size) {
        return null;
    }

    @Override
    public ApiResponse addOutstanding(ClientDto clientDto) {
        return null;
    }

    @Override
    public ApiResponse updateOutstanding(int id, ClientDto clientDto) {
        return null;
    }

    @Override
    public ApiResponse deleteOutstanding(String clientId) {
        return null;
    }
}