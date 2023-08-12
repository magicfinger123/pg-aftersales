/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.io.entity.SlaEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.SlaUpdateRequest;
import com.pgagtersales.pgaftersales.service.SlaService;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/service-level")
public class SlaController
{
    @Autowired
    private
    SlaService slaService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateGeneratorService(@RequestBody SlaUpdateRequest slaUpdateDto)
    {
        SlaEntity slaEntity = new SlaEntity();
        BeanUtils.copyProperties(slaUpdateDto, slaEntity);
        slaEntity.setActive(true);
        ApiResponse apiResponse = slaService.updateGenService(slaEntity);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/notify")
    public ResponseEntity<ApiResponse> slaRequest(@RequestBody SlaRequestDto slaRequestDto)
    {
        ApiResponse apiResponse = slaService.notifyCustomer(slaRequestDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/priceList")
    public ResponseEntity<ApiResponse> updatePriceList(@RequestBody SlaPriceListDto slaPriceListDto)
    {
        ApiResponse apiResponse = slaService.updatePriceList(slaPriceListDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/priceList")
    public ResponseEntity<ApiResponse> getPriceList()
    {
        ApiResponse apiResponse = slaService.getAllSla(0,30);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
