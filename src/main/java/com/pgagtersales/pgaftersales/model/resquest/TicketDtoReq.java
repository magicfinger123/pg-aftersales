/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */
package com.pgagtersales.pgaftersales.model.resquest;
import lombok.Data;


@Data
public class TicketDtoReq {
    private String description;
    private String clientId;
    private int Status;
    private String remark;
    private String priority;
    private String startDate;
    private String endDate;
}
