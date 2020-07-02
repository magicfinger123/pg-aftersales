/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.io.entity.OutstandingEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.GeneratorRepository;
import com.pgagtersales.pgaftersales.repository.OutstandingRepository;
import com.pgagtersales.pgaftersales.service.DashboardService;
import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import com.pgagtersales.pgaftersales.shared.dto.DashboardDto;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.OutstandingDto;
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
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    GeneratorRepository generatorRepository;

    @Autowired
    OutstandingRepository outstandingRepository;
    @Autowired
    private ResponseBuilder responseBuilder;

    @Override
    public ApiResponse initDashBoard() {
        List<ClientDto> clientListDto = new ArrayList<>();
        List<OutstandingDto> outstandingDtos = new ArrayList<>();
        List<GeneratorDto> genListDto = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<ClientsEntity> clientPage = clientRepository.findAll(pageable);
        if (clientPage == null || clientPage.isEmpty()) {
            //throw new UserServiceException("Unable to init Dashboard","client list returned null");
        } else {
            List<ClientsEntity> clientList = clientPage.getContent();
            for (ClientsEntity client : clientList) {
                ClientDto clientDto = new ClientDto();
                BeanUtils.copyProperties(client, clientDto);
                clientListDto.add(clientDto);
                if (client.getGeneratorDtos()!=null){
                    for (GeneratorEntity gen:client.getGeneratorDtos()) {
                        GeneratorDto genDto = modelMapper.map(gen, GeneratorDto.class);
                        genDto.setClientName(client.getFirst_name());
                        genListDto.add(genDto);
                    }

                }
                if (client.getOutstandingDtos()!=null){
                    for (OutstandingEntity out:client.getOutstandingDtos()) {
                        OutstandingDto outDto = modelMapper.map(out, OutstandingDto.class);
                        outDto.setCompanyName(client.getCompany());
                        outDto.setClientName(client.getFirst_name());
                        outstandingDtos.add(outDto);
                    }

                }
            }
        }

       /* List<GeneratorEntity> generatorPage = generatorRepository.findAllByOrderByDateDesc();
        if (generatorPage == null || generatorPage.isEmpty()) {
            //throw new UserServiceException("Unable to init Dashboard","generator list returned null");
        } else {
            List<GeneratorEntity> generators = generatorPage;
            for (GeneratorEntity gen : generators) {
                GeneratorDto genDto = new GeneratorDto();
                BeanUtils.copyProperties(gen, genDto);

                genListDto.add(genDto);
            }
        }

        Page<OutstandingEntity> outstanding = outstandingRepository.findAll(pageable);
        if (outstanding == null || outstanding.isEmpty()) {
          //  throw new UserServiceException("Something went wrong","outstanding returned null");
        } else {
            List<OutstandingEntity> outstandingEntities = outstanding.getContent();
            for (OutstandingEntity out : outstandingEntities) {
                ClientsEntity client = clientRepository.findById(Integer.parseInt(out.getClientId()));
                if (client != null) {
                    OutstandingDto outDto = new OutstandingDto();
                    BeanUtils.copyProperties(out, outDto);
                    outDto.setClientName(client.getFirst_name());
                    outDto.setCompanyName(client.getCompany());
                    outstandingDtos.add(outDto);
                }
            }
        }*/
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setClientDtos(clientListDto);
        dashboardDto.setGeneratorDtos(genListDto);
        dashboardDto.setOutstandingDtos(outstandingDtos);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(dashboardDto);
        return apiResponse;


    }
}
