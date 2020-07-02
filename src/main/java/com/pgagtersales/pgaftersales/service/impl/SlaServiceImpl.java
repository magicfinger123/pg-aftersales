/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ScheduleEntity;
import com.pgagtersales.pgaftersales.io.entity.SlaEntity;
import com.pgagtersales.pgaftersales.io.entity.SlaPriceListEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ActionResponse;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.SlaPriceListRepository;
import com.pgagtersales.pgaftersales.repository.SlaRepository;
import com.pgagtersales.pgaftersales.service.SlaService;
import com.pgagtersales.pgaftersales.shared.dto.ScheduleDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaRequestDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SlaServiceImpl implements SlaService {
    @Autowired
    SlaRepository slaRepository;
    @Autowired
    SlaPriceListRepository slaPriceListRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    private SendMail sendMail;

    @Autowired
    NotificationMessages message;

    String[] recipent = {"ossaimike8@gmail.com","exc.easey@gmail.com"};
    @Override
    public ApiResponse updateGenService(SlaEntity slaEntity) {
        if (slaEntity==null){
            throw new UserServiceException("sla is null","sla is null");
        }
        System.out.println("sla entity: "+slaEntity);
        SlaEntity slaEntity1 = slaRepository.save(slaEntity);
        if (slaEntity1 == null){
            throw new UserServiceException("something went wrong","Sla returned null");
        }else{
            ActionResponse actionResponse = ActionResponse.builder()
                    .actionStatus("Successful")
                    .action("Updated Generator Service Details")
                    .build();
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(actionResponse);
            return apiResponse;
        }
    }

    @SneakyThrows
    @Override
    public ApiResponse notifyCustomer(SlaRequestDto slaRequestDto) {
        SlaPriceListEntity priceList = slaPriceListRepository.findByRating(slaRequestDto.getGenCategory());
        if (priceList == null){
            throw new UserServiceException("Wrong input","genCategory not in priceList"+slaRequestDto.getGenCategory());
        }
        switch (slaRequestDto.getSlaType()){
            case "sixService":
                slaRequestDto.setAmount(priceList.getSixService());
                slaRequestDto.setSlaType("6 services");
                break;
            case "eightService":
                slaRequestDto.setAmount(priceList.getEightService());
                slaRequestDto.setSlaType("8 services");
                break;
            case "tenService":
                slaRequestDto.setAmount(priceList.getTenService());
                slaRequestDto.setSlaType("10 services");
                break;
            case "twelveService":
                slaRequestDto.setAmount(priceList.getTwelveService());
                slaRequestDto.setSlaType("12 services");
                break;
        }
        sendMail.sendEmailWithAttachment(message.slaRequestotification(slaRequestDto),recipent,recipent, "Sla notice");
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Site Inspection Logged Successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }


    @Override
    public ApiResponse updatePriceList(SlaPriceListDto slaPriceListDto) {
        SlaPriceListEntity slaPriceListEntity = new SlaPriceListEntity();
        BeanUtils.copyProperties(slaPriceListDto, slaPriceListEntity);
        SlaPriceListEntity priceList = slaPriceListRepository.save(slaPriceListEntity);
        SlaPriceListDto returnValue = new SlaPriceListDto();
        BeanUtils.copyProperties(priceList,returnValue);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }
 /*   public boolean checknull(SlaEntity slaEntity) throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
            return true;
        }*/
    }

