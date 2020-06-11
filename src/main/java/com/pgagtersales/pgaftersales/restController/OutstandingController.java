/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.io.entity.OutstandingEntity;
import com.pgagtersales.pgaftersales.io.entity.SlaEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.SlaUpdateRequest;
import com.pgagtersales.pgaftersales.service.OutstandingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outstanding")
public class OutstandingController {
    @Autowired
    private
    OutstandingService outstandingService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> updateGeneratorService(@RequestBody int page,int size)
    {
        ApiResponse apiResponse = outstandingService.getOutstanding(page, size);
        return ResponseEntity.ok(apiResponse);
    }
}
