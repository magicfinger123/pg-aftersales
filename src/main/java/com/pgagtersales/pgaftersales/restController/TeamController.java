/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.TeamCreationRequest;
import com.pgagtersales.pgaftersales.security.AuthorizationFilter;
import com.pgagtersales.pgaftersales.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/team")
@RestController
public class TeamController {
    @Autowired
    private
    TeamService teamService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTeam(@RequestBody TeamCreationRequest teamCreationRequest){
        System.out.println("teamCreationn: "+teamCreationRequest);
        Long startTime = System.currentTimeMillis();
        apiResponse = teamService.createTeam(teamCreationRequest);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllTeam(@RequestParam(value = "page",defaultValue = "1") int page,  @RequestParam(value = "size",defaultValue = "25") int size){
        Long startTime = System.currentTimeMillis();
        apiResponse = teamService.getAllTeams(page,size);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/by_userId")
    public ResponseEntity<ApiResponse> getTeamByUserId( @RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "25") int size){
        Long startTime = System.currentTimeMillis();
        apiResponse = teamService.getByUserId(page,size);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
