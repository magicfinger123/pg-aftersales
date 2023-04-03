/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.VehicleInspectionService;
import com.pgagtersales.pgaftersales.shared.dto.VehicleInspectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vehicle")
@RestController
public class VehicleReportController {
        @Autowired
        private
        VehicleInspectionService vehicleInspectionService;
        @Autowired
        private
        ApiResponse apiResponse;
        @Autowired
        private
        LogTimeFilter logTimeFilter;
        @PostMapping()
        public ResponseEntity<ApiResponse> addTicket(@RequestBody VehicleInspectionDto vehicleInspection)
        {
            ApiResponse apiResponse = vehicleInspectionService.vehicleInspectionReport(vehicleInspection);
            return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
        }
}
