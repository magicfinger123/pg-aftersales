/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.resquest;
import lombok.Data;

import java.sql.Date;

@Data
public class SlaUpdateRequest {
    private int client_id;
    private int gen_id;
    private Date slaStartDdate;
    private int noOfService;
    private int hourReadingStart;
    private int lastServiceHour;
    private Date lastServiceDate;
    private int serviceDone;
}
