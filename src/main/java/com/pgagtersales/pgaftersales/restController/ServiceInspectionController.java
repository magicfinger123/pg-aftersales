/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.ServiceInspectionService;
import com.pgagtersales.pgaftersales.shared.dto.ServiceInspectionDto;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service_report")
public class ServiceInspectionController {

    @Autowired
    private
    ServiceInspectionService serviceInspectionService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;
    @SneakyThrows
    @PostMapping("/{notify}")
    public ResponseEntity<ApiResponse> updateServiceReport(@RequestBody ServiceInspectionDto serviceInspectionDto, @PathVariable int notify)
    {
        Boolean notification = false;
        if (notify == 1){
            notification = true;
        }
        Long startTime = System.currentTimeMillis();
        Long duration = System.currentTimeMillis()-startTime;
        ApiResponse apiResponse = serviceInspectionService.addServiceInspection(serviceInspectionDto,notification);
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.ok(apiResponse);
    }
}
