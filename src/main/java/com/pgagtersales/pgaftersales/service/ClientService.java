/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ApiResponse getClients(int page, int size);
    ApiResponse searchClients(String alias, int page, int size);

    ApiResponse getClientByUsername(String clientId);
}
