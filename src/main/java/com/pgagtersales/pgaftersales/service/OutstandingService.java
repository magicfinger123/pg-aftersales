/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.ClientDtoReq;

public interface OutstandingService {
    ApiResponse getOutstanding(int page, int size);
    ApiResponse searchOutstandingbyClient(String alias, int page, int size);
    ApiResponse addOutstanding(ClientDtoReq clientDto);
    ApiResponse updateOutstanding(int id, ClientDtoReq clientDto);
    ApiResponse deleteOutstanding(String clientId);
}
