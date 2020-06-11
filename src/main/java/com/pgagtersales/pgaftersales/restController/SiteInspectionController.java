/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.SiteInspectionService;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site_inspection")
public class SiteInspectionController {
    @Autowired
    private
    SiteInspectionService siteInspectionService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;

    @PostMapping("/{notify}")
    public ResponseEntity<ApiResponse> updateGeneratorService(@RequestBody SiteInspectionDto siteInspectionDto, @PathVariable int notify)
    {
        Boolean notification = false;
        if (notify == 1){
            notification = true;
        }
        Long startTime = System.currentTimeMillis();
        //ApiResponse apiResponse = genService.getGenerator(id);
        Long duration = System.currentTimeMillis()-startTime;
        ApiResponse apiResponse = siteInspectionService.addSiteInspection(siteInspectionDto,notification);
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.ok(apiResponse);
    }
}
