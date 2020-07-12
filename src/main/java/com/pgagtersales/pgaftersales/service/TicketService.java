/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.google.protobuf.Api;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.TicketDtoReq;
import com.pgagtersales.pgaftersales.shared.dto.TicketsDto;

public interface TicketService {
    ApiResponse addTicket(TicketDtoReq ticketsDto);
    ApiResponse updateTicket(TicketsDto ticketsDto);
    ApiResponse getTIcketByClientId(String clientId);
    ApiResponse getTicketByStatus(int status);
    ApiResponse getAllTicket(int page, int size);
}
