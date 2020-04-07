/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.model.response.*;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.service.ClientService;
import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ResponseBuilder responseBuilder;


    @Override
    public ApiResponse getClients(int page, int size) {
        List<ClientDto> returnedValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientsEntity> clientPage = clientRepository.findAll(pageable);
        if (clientPage == null || clientPage.isEmpty()) {
            ApiResponse failedResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .developerMessage("User not found")
                    .userMessage("User not found")
                    .build();
            failedResponse.responseEntity = ResponseEntity.ok(errorMessage);
            return failedResponse;
        } else {
            List<ClientsEntity> clientList = clientPage.getContent();
            for (ClientsEntity client : clientList) {
                ClientDto clientDto = new ClientDto();
                BeanUtils.copyProperties(client, clientDto);
                returnedValue.add(clientDto);
            }
            ApiResponse getClients = responseBuilder.successfullResponse();
            getClients.responseEntity = ResponseEntity.ok(returnedValue);
            return getClients;
        }
    }

    @Override
    public ApiResponse searchClients(String alias, int page, int size) {
        List<ClientDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientsEntity> clientsPage = clientRepository.searchClient(alias, pageable);
        if (clientsPage == null || clientsPage.isEmpty()) {
            ApiResponse failedResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .developerMessage("search result returned null")
                    .userMessage("No result")
                    .build();
            failedResponse.responseEntity = ResponseEntity.status(failedResponse.getStatusCode()).body(errorMessage);
            return failedResponse;
        } else {
            List<ClientsEntity> clients = clientsPage.getContent();
            for (ClientsEntity clientEntity : clients) {
                ClientDto client = new ClientDto();
                BeanUtils.copyProperties(clientEntity, client);
                returnValue.add(client);
            }
            ApiResponse getClients = responseBuilder.successfullResponse();
            getClients.responseEntity = ResponseEntity.status(getClients.getStatusCode()).body(returnValue);
            return getClients;
        }
    }

    @Override
    public ApiResponse getClientByUsername(String username) {
       ClientsEntity clientsEntity = clientRepository.findByUsername(username);
       if (clientsEntity == null ){
           ApiResponse failedResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
           ErrorMessage errorMessage = ErrorMessage.builder()
                   .developerMessage(username+" not found")
                   .userMessage(username+" not found")
                   .build();
           failedResponse.responseEntity = ResponseEntity.status(failedResponse.getStatusCode()).body(errorMessage);
           return failedResponse;

       }
       else{
           ClientDto clientDto = new ClientDto();
           BeanUtils.copyProperties(clientsEntity,clientDto);
           ApiResponse successresponse = responseBuilder.successfullResponse();
           successresponse.responseEntity = ResponseEntity.ok(clientDto);
           return  successresponse;

       }
    }
}
