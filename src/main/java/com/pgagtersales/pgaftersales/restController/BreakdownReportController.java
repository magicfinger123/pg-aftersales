/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.BreakdownReportService;
import com.pgagtersales.pgaftersales.shared.dto.BreakdownReportDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/breakdown")
public class BreakdownReportController {
    @Autowired
    private
    BreakdownReportService breakdownReportService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;
    @SneakyThrows
    @PostMapping("/{notify}")
    public ResponseEntity<ApiResponse> updateServiceReport(@RequestBody BreakdownReportDto breakdownReportDto, @PathVariable int notify)
    {
        Boolean notification = false;
        if (notify == 1){
            notification = true;
        }
        Long startTime = System.currentTimeMillis();
        Long duration = System.currentTimeMillis()-startTime;
        ApiResponse apiResponse = breakdownReportService.addBreakdownReport(breakdownReportDto,notification);
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
