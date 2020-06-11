/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.ClientDto;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ApiResponse getClients(int page, int size);
    ApiResponse searchClients(String alias, int page, int size);
    ApiResponse getClientByUsername(String clientId);
    ApiResponse getClientById(int id);
    ApiResponse addClient(ClientDto clientDto);
    ApiResponse updateClient(int id, ClientDto clientDto);
    ApiResponse deleteClient(int id);
}
