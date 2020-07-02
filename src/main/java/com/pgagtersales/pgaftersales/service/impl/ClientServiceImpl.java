/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.model.response.*;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.service.ClientService;
import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import org.modelmapper.ModelMapper;
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
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Override
    public ApiResponse getClients(int page, int size) {
        System.out.println("pagez "+page);
        List<ClientDto> returnedValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientsEntity> clientPage = clientRepository.findAll(pageable);
        if (clientPage == null || clientPage.isEmpty()) {
            throw new UserServiceException("Empty list returned","empty list returned");
        } else {
            List<ClientsEntity> clientList = clientPage.getContent();
            for (ClientsEntity client : clientList) {
                ClientDto clientDto = new ClientDto();
                BeanUtils.copyProperties(client, clientDto);
                returnedValue.add(clientDto);
            }
            ApiResponse getClients = responseBuilder.successfulResponse();
            getClients.responseEntity = ResponseEntity.ok(returnedValue);

            return getClients;
        }
    }

    @Override
    public ApiResponse searchClients(String alias, int page, int size) {
        List<ClientDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page-1, size);
        Page<ClientsEntity> clientsPage = clientRepository.searchClient(alias, pageable);
        if (clientsPage == null || clientsPage.isEmpty()) {
            throw new UserServiceException("client not found","search not found");
        } else {
            List<ClientsEntity> clients = clientsPage.getContent();
            for (ClientsEntity clientEntity : clients) {
                ClientDto client = new ClientDto();
                BeanUtils.copyProperties(clientEntity, client);
                returnValue.add(client);
            }
            ApiResponse getClients = responseBuilder.successfulResponse();
            getClients.responseEntity = ResponseEntity.status(getClients.getStatusCode()).body(returnValue);
            return getClients;
        }
    }

    @Override
    public ApiResponse getClientByUsername(String username) {
        ClientsEntity clientsEntity = clientRepository.findByUsername(username);
        if (clientsEntity == null) {
            ApiResponse failedResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .developerMessage(username + " not found")
                    .userMessage(username + " not found")
                    .build();
            failedResponse.responseEntity = ResponseEntity.status(failedResponse.getStatusCode()).body(errorMessage);
            return failedResponse;

        } else {
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientsEntity, clientDto);
            ApiResponse successresponse = responseBuilder.successfulResponse();
            successresponse.responseEntity = ResponseEntity.ok(clientDto);
            return successresponse;

        }
    }

    @Override
    public ApiResponse getClientById(int clientId) {
        ClientsEntity clientsEntity = clientRepository.findById(clientId);
        if (clientsEntity == null) {
            ApiResponse failedResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .developerMessage(clientId + " not found")
                    .userMessage(clientId + " not found")
                    .build();
            failedResponse.responseEntity = ResponseEntity.status(failedResponse.getStatusCode()).body(errorMessage);
            return failedResponse;

        } else {
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientsEntity, clientDto);
            ApiResponse successresponse = responseBuilder.successfulResponse();
            successresponse.responseEntity = ResponseEntity.ok(clientDto);
            return successresponse;

        }
    }

    @Override
    public ApiResponse addClient(com.pgagtersales.pgaftersales.model.resquest.ClientDto clientDto) {
        //ClientDto returnValue = new ClientDto();
        ClientsEntity client = clientRepository.findByUsername(clientDto.getUsername());
        if (client != null) {
            throw new UserServiceException("client already exist", "client already exist");
        }
       // ClientsEntity clientsEntity = new ClientsEntity();
       // BeanUtils.copyProperties(clientDto, clientsEntity);
        ModelMapper modelMapper = new ModelMapper();
        ClientsEntity clientsEntity = modelMapper.map(clientDto, ClientsEntity.class);
        ClientsEntity saveUser = clientRepository.save(clientsEntity);
       // BeanUtils.copyProperties(saveUser, returnValue);
        ClientDto returnValue  = modelMapper.map(saveUser, ClientDto.class);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }

    @Override
    public ApiResponse updateClient(int id, com.pgagtersales.pgaftersales.model.resquest.ClientDto clientDto) {
        ClientDto returnValue = new ClientDto();
        ClientsEntity client = clientRepository.findById(id);
        if (client == null) {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .userMessage("Error Occured")
                    .developerMessage("Id not found")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            BeanUtils.copyProperties(clientDto, client);
            ClientsEntity saveUser = clientRepository.save(client);
            BeanUtils.copyProperties(saveUser, returnValue);
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }
    @Override
    public ApiResponse deleteClient(int id) {
        ClientDto returnValue = new ClientDto();
        ClientsEntity client = clientRepository.findById(id);
        if (client == null) {
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Unable to delete client")
                    .developerMessage("client not in database")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            clientRepository.delete(client);
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok("Successfully deleted client with "+client.getFirst_name());
            return apiResponse;

        }
    }
}
