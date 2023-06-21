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
import com.pgagtersales.pgaftersales.shared.dto.OutstandingDto;
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
    public ResponseEntity<ApiResponse> getOutstanding(@RequestBody int page,int size)
    {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = outstandingService.getOutstanding(page, size);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> addOutstanding(@RequestBody OutstandingDto outstandingDto)
    {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = outstandingService.addOutstanding(outstandingDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOutstanding(@PathVariable("id") int id, @RequestBody OutstandingDto outstandingDto)
    {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = outstandingService.updateOutstanding(id, outstandingDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
