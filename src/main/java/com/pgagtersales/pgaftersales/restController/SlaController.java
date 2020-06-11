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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return ResponseEntity.ok(apiResponse);
    }
}
