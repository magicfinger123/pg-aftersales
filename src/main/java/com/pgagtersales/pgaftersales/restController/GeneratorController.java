package com.pgagtersales.pgaftersales.restController;


import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.GeneratorService;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
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
    public List<GeneratorDto> getGenerators(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "25") int limit) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        List<GeneratorDto> generatorDtos = genService.getGenerators(page,limit);
        for (GeneratorDto generator: generatorDtos) {
            returnValue.add(generator);
        }
        return returnValue;
    }
    @GetMapping(path = "/search/{alias}")
    public List<GeneratorDto> searchGenerators(@PathVariable String alias, @RequestParam(value = "page",defaultValue = "1") int page,  @RequestParam(value = "size",defaultValue = "25") int size)
    {
        List<GeneratorDto> returnValue = new ArrayList<>();
        List<GeneratorDto> generatorDtos = genService.searchGenerators(alias,page,size);
        for (GeneratorDto generator: generatorDtos) {
            returnValue.add(generator);
        }
        return returnValue;
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
}
