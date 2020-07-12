/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.DashboardService;
import com.pgagtersales.pgaftersales.service.EmailRecipentService;
import com.pgagtersales.pgaftersales.shared.dto.EmailsDto;
import com.pgagtersales.pgaftersales.shared.dto.VehicleInspectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emails")
@RestController
public class EmailsRecipentController {
    @Autowired
    private
    EmailRecipentService emailRecipentService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;
    @PostMapping()
    public ResponseEntity<ApiResponse> getEmail(@RequestBody EmailsDto emailsDto)
    {
        ApiResponse apiResponse = emailRecipentService.addEmailRecipent(emailsDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEmail(@PathVariable String id,@RequestBody EmailsDto emailsDto)
    {
        ApiResponse apiResponse = emailRecipentService.upDateEmailRecipents(id,emailsDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse> getByType(@PathVariable String type)
    {
        ApiResponse apiResponse = emailRecipentService.getEMailRecipent(type);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
