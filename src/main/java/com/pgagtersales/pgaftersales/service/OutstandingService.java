/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.ClientDto;

public interface OutstandingService {
    ApiResponse getOutstanding(int page, int size);
    ApiResponse searchOutstandingbyClient(String alias, int page, int size);
    ApiResponse addOutstanding(ClientDto clientDto);
    ApiResponse updateOutstanding(int id, ClientDto clientDto);
    ApiResponse deleteOutstanding(String clientId);
}
