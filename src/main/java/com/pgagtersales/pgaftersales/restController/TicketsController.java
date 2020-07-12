/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.TicketDtoReq;
import com.pgagtersales.pgaftersales.service.TeamService;
import com.pgagtersales.pgaftersales.service.TicketService;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaRequestDto;
import com.pgagtersales.pgaftersales.shared.dto.TicketsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
    @Autowired
    private
    TicketService ticketService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTicket(@RequestBody TicketDtoReq ticketsDto)
    {
        ApiResponse apiResponse = ticketService.addTicket(ticketsDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateTicket(@RequestBody TicketsDto ticketsDto)
    {
        ApiResponse apiResponse = ticketService.updateTicket(ticketsDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/getByStatus/{status}")
    public ResponseEntity<ApiResponse> getByStatus(@PathVariable int status)
    {
        ApiResponse apiResponse = ticketService.getTicketByStatus(status);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/getByClient/{clientId}")
    public ResponseEntity<ApiResponse> getByStatus(@PathVariable String clientId)
    {
        ApiResponse apiResponse = ticketService.getTIcketByClientId(clientId);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getByAll(@RequestParam(value = "page",defaultValue = "0")int page, @RequestParam(value = "size",defaultValue = "25")int size)
    {
        ApiResponse apiResponse = ticketService.getAllTicket(page,size);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

}
