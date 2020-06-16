/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.ScheduleCreationDto;
import com.pgagtersales.pgaftersales.model.resquest.ScheduleTaskCreationDto;
import com.pgagtersales.pgaftersales.service.ScheduleService;
import com.pgagtersales.pgaftersales.service.TeamService;
import com.pgagtersales.pgaftersales.shared.dto.ScheduleTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    @Autowired
    private
    ScheduleService scheduleService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createSchedule(@RequestBody ScheduleCreationDto scheduleCreationDto){
        System.out.println("ScheduleCreation: "+scheduleCreationDto);
        Long startTime = System.currentTimeMillis();
        apiResponse = scheduleService.createSchedule(scheduleCreationDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllSchedule(@RequestParam(value = "page",defaultValue = "1") int page,  @RequestParam(value = "size",defaultValue = "25") int size){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = scheduleService.getAllSchedule(page,size);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/by_schedule/{id}")
    public ResponseEntity<ApiResponse> getScheduleByScheduleId(@PathVariable("id") String scheduleId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = scheduleService.getScheduleByScheduleId(scheduleId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/by_team/{id}")
    public ResponseEntity<ApiResponse> getScheduleByTeamId(@PathVariable("id") String teamId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = scheduleService.getScheduleByTeamId(teamId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/task")
    public ResponseEntity<ApiResponse> createTask(@RequestBody ScheduleTaskCreationDto scheduleTaskCreationDto){
        System.out.println("ScheduleCreation: "+scheduleTaskCreationDto);
        Long startTime = System.currentTimeMillis();
        apiResponse = scheduleService.addTask(scheduleTaskCreationDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PutMapping("/task")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody ScheduleTaskDto scheduleTaskDto){
        System.out.println("taskUpdate: "+scheduleTaskDto);
        Long startTime = System.currentTimeMillis();
        apiResponse = scheduleService.updateTask(scheduleTaskDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable("taskId") String taskId){
        System.out.println("task delete: "+taskId);
        Long startTime = System.currentTimeMillis();
        apiResponse = scheduleService.deleteTask(taskId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/task/scheduleId/{id}")
    public ResponseEntity<ApiResponse> getTaskByScheduleId(@PathVariable("id") String scheduleId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = scheduleService.getTaskByScheduleId(scheduleId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
