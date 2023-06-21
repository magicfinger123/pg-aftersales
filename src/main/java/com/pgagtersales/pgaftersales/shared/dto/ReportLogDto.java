/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.annotation.Nullable;
import javax.persistence.Column;
import java.sql.Date;

@Data
public class ReportLogDto {
    private String userId;
    private String description;
    private String action;
    private Date date;
    private String status;
    private String time;
    @Nullable
    private String user;
}
