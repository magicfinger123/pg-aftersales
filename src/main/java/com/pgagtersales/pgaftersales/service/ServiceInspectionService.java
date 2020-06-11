/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.io.entity.ServiceInspectionEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.ServiceInspectionDto;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;

public interface ServiceInspectionService {
    ApiResponse addServiceInspection(ServiceInspectionDto serviceInspectionDto, Boolean sendNotification) throws Exception;
}
