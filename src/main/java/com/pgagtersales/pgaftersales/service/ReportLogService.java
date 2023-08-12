/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportSubmissionDto;
import org.springframework.data.domain.Pageable;

public interface ReportLogService {
    ApiResponse getUserReportByDate(String alias1, String alias2, int page, int size);
    ApiResponse addLog(ReportLogDto reportLogDto);
    ApiResponse submitReport(ReportSubmissionDto reportSubmissionDto);
    ApiResponse getAllReports();

    ApiResponse getReportByUser(String userID, int page, int size);
}
