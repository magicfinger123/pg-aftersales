/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportSubmissionDto {
    private String userId;
    private String fullname;
    private String department;
    private String date;
    private List<ReportLogDto> reportLogDtos;
}

