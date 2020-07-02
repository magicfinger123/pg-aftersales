package com.pgagtersales.pgaftersales.restController;


import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.UserRest;
import com.pgagtersales.pgaftersales.service.GeneratorService;
import com.pgagtersales.pgaftersales.service.impl.UserServiceImpl;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/generators")
public class GeneratorController {

    @Autowired
    private GeneratorService genService;

    @GetMapping
    public ResponseEntity getGenerators(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "limit",defaultValue = "25") int limit) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        ApiResponse apiResponse = genService.getGenerators(page,limit);
        return  ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping(path = "/search/{alias}")
    public ResponseEntity searchGenerators(@PathVariable String alias, @RequestParam(value = "page",defaultValue = "1") int page,  @RequestParam(value = "size",defaultValue = "25") int size)
    {
        ApiResponse apiResponse = genService.searchGenerators(alias,page,size);
        return  ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping(path = "/getGenByClientId/{clientId}")
    public ResponseEntity<ApiResponse> getGeneratorByclientId(@PathVariable String clientId){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = genService.getGenByClientId(clientId);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping(path = "/getGenById/{id}")
    public ResponseEntity<ApiResponse> getGeneratorByGeneratorId(@PathVariable int id){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = genService.getGenerator(id);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping(path = "/getcb/{id}")
    public ResponseEntity<ApiResponse> getGenerator(@PathVariable int id){
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = genService.getGenByClientDto(String.valueOf(id));
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping(path = "/generator_commission/{notify}")
    public ResponseEntity<ApiResponse> addGenerators(@RequestBody GeneratorDto generatorDto, @PathVariable int notify)
    {
        Long startTime = System.currentTimeMillis();
        Boolean sendNotification = false;
        if (notify == 1){
            sendNotification = true;
        }
        ApiResponse apiResponse = genService.addGenerator(generatorDto, sendNotification);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PutMapping(path = "/generator_activity/{id}")
    public ResponseEntity<ApiResponse> updateGenerators(@PathVariable int id, @RequestBody GeneratorDto generatorDto)
    {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = genService.updateGenerator(id, generatorDto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @DeleteMapping(path = "/generator_activity/{id}")
    public ResponseEntity<ApiResponse> deleteGenerators(@PathVariable int id)
    {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = genService.deleteGenerator(id);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
