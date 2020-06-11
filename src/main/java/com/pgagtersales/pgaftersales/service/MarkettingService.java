/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.MarkettingDto;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;

public interface MarkettingService {
    ApiResponse addNewMarkettingLead(MarkettingDto markettingDto);
}
