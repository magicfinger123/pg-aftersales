/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.TeamEntity;
import com.pgagtersales.pgaftersales.io.entity.TicketEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.TicketDtoReq;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.TeamRepository;
import com.pgagtersales.pgaftersales.repository.TicketRepository;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.TicketService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.TicketsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private Utils utils;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public ApiResponse addTicket(TicketDtoReq ticketsDto) {
        ClientsEntity client = clientRepository.findById(Integer.parseInt(ticketsDto.getClientId()));
        if (client == null){
            throw new UserServiceException("client not found","client not found");
        }
        TicketEntity ticketEntity = new TicketEntity();//modelMapper.map(ticketsDto, TicketEntity.class);
        BeanUtils.copyProperties(ticketsDto,ticketEntity);
      //  ticketEntity.setId(0);
        ticketRepository.save(ticketEntity);
        ApiResponse apiResponse = utils.sucessApiResponse("ticket has been added");
        return apiResponse;
    }

    @Override
    public ApiResponse updateTicket(TicketsDto ticketsDto) {
        TicketEntity tc = ticketRepository.findById(ticketsDto.getId());
        if (tc == null){
            throw new UserServiceException("ticket not found", "ticket not found");
        }
        BeanUtils.copyProperties(ticketsDto, tc);
        TicketEntity returnValue = ticketRepository.save(tc);
        if (returnValue == null){
            throw new UserServiceException("something went wrong","something went wronng");
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }

    @Override
    public ApiResponse getTIcketByClientId(String clientId) {
        ClientsEntity client = clientRepository.findById(Integer.parseInt(clientId));
        if (client == null){
            throw new UserServiceException("client not found","client not found");
        }
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<TicketEntity> tickets = ticketRepository.findByClientId(clientId, pageable);
        List<TicketEntity> ticketEntities = tickets.getContent();
        List<TicketsDto> returnValue = new ArrayList<>();
        for (TicketEntity ticket:ticketEntities) {
            TicketsDto ticketDto = new TicketsDto();//modelMapper.map(ticket, TicketsDto.class);
            BeanUtils.copyProperties(ticket,ticketDto);
            ticketDto.setClientName(client.getFirst_name());
            ticketDto.setCompanyName(client.getCompany());
            returnValue.add(ticketDto);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }
    @Override
    public ApiResponse getTicketByStatus(int status) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<TicketEntity> tickets = ticketRepository.findByStatus(status, pageable);
        List<TicketEntity> ticketEntities = tickets.getContent();
        List<TicketsDto> returnValue = new ArrayList<>();
        for (TicketEntity ticket:ticketEntities) {
            TicketsDto ticketDto = modelMapper.map(ticket, TicketsDto.class);
            ClientsEntity client = clientRepository.findById(Integer.parseInt(ticket.getClientId()));
            if (client != null){
                ticketDto.setClientName(client.getFirst_name());
                ticketDto.setCompanyName(client.getCompany());
            }
            returnValue.add(ticketDto);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }

    @Override
    public ApiResponse getAllTicket(int page, int size) {
        List<TicketsDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<TicketEntity> tickets = ticketRepository.findAll(pageable);
        List<TicketEntity> ticketEntities = tickets.getContent();
        for (TicketEntity ent:ticketEntities
             ) {
            TicketsDto ticket = modelMapper.map(ent, TicketsDto.class);
            ClientsEntity client = clientRepository.findById(Integer.parseInt(ticket.getClientId()));
            if (client != null){
                ticket.setClientName(client.getFirst_name());
                ticket.setCompanyName(client.getCompany());
            }
            returnValue.add(ticket);
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }
}
