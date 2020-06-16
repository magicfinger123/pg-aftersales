/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.InventoryCreationDto;
import com.pgagtersales.pgaftersales.model.resquest.ScheduleCreationDto;
import com.pgagtersales.pgaftersales.service.InventoryService;
import com.pgagtersales.pgaftersales.service.ScheduleService;
import com.pgagtersales.pgaftersales.shared.dto.InventoryItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.LineNumberReader;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private
    InventoryService inventoryService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInventory(@RequestBody InventoryCreationDto inventoryCreationDto){
        System.out.println("ScheduleCreation: "+ inventoryCreationDto+ " at InventoryController");
        Long startTime = System.currentTimeMillis();
        apiResponse = inventoryService.createInventory(inventoryCreationDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllInventory(@RequestParam(value = "page",defaultValue = "1") int page,  @RequestParam(value = "size",defaultValue = "25") int size){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.getAllInventory(page,size);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/by_schedule/{id}")
    public ResponseEntity<ApiResponse> getInventoryByScheduleId(@PathVariable("id") String scheduleId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.getInventoryByScheduleId(scheduleId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/by_team/{id}")
    public ResponseEntity<ApiResponse> getInventoryByTeamId(@PathVariable("id") String teamId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.getInventoryByTeamId(teamId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/item")
    public ResponseEntity<ApiResponse> addItemToInventory(@RequestBody InventoryItemDto inventoryItemDto){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.addItemToInventory(inventoryItemDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PutMapping("/item")
    public ResponseEntity<ApiResponse> updateInventoyItem(@RequestBody InventoryItemDto inventoryItemDto){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.upDateInventoryItem(inventoryItemDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/item")
    public ResponseEntity<ApiResponse> getItemByInventoryId(@RequestParam("id") String inventoryId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.getItemsByInventoryId(inventoryId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @DeleteMapping("/item")
    public ResponseEntity<ApiResponse> deleteItem(@RequestParam("id") String itemId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = inventoryService.deleteInventoryItem(itemId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
