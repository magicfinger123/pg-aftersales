/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.io.entity.SlaEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.SlaUpdateRequest;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaRequestDto;
import org.springframework.stereotype.Service;


public interface SlaService {
    ApiResponse updateGenService(SlaEntity slaEntity);
    ApiResponse notifyCustomer(SlaRequestDto slaRequestDto);
    ApiResponse updatePriceList(SlaPriceListDto slaPriceListDto);
}
