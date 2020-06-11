/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.MarkettingService;
import com.pgagtersales.pgaftersales.service.OutstandingService;
import com.pgagtersales.pgaftersales.shared.dto.MarkettingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prospective_client")
public class MarkettingController {
    @Autowired
    private
    MarkettingService markettingService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;

    @PostMapping()
    public ResponseEntity<ApiResponse> addNewProspectiveClient(@RequestBody MarkettingDto markettingDto)
    {
        ApiResponse apiResponse = markettingService.addNewMarkettingLead(markettingDto);
        return ResponseEntity.ok(apiResponse);
    }
}
