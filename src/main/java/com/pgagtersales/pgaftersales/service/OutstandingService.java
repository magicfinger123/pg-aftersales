/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.ClientDtoReq;
import com.pgagtersales.pgaftersales.shared.dto.OutstandingDto;

public interface OutstandingService {
    ApiResponse getOutstanding(int page, int size);
    ApiResponse searchOutstandingByClient(String alias, int page, int size);
    ApiResponse addOutstanding(OutstandingDto outstandingDto);
    ApiResponse updateOutstanding(int id, OutstandingDto outstandingDto);
    ApiResponse deleteOutstanding(String clientId);
}
