package com.pgagtersales.pgaftersales.restController;


import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@ResponseBody
@RequestMapping("/clients")
public
class ClientsController {
    @Autowired
    private
    ClientService clientService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;

    @GetMapping(path = "/search/{alias}")
    public
    ResponseEntity<ApiResponse> searchClients(@PathVariable String alias, @RequestParam(value = "page",defaultValue = "0")int page, @RequestParam(value = "size",defaultValue = "25")int size)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse returnedValue  = clientService.searchClients(alias,page,size);
        returnedValue.requestedCommand = "/clients";
        long duration = System.currentTimeMillis() - startTime;
        returnedValue.executionTime = Double.valueOf(duration)/1000;
        System.out.println(ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue));
        return ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue);

    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllClients(@RequestParam(value = "page",defaultValue = "0")int page, @RequestParam(value = "size",defaultValue = "25")int size)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse returnedValue = clientService.getClients(page,size);
        returnedValue.requestedCommand = "/clients";
        long duration = System.currentTimeMillis() - startTime;
        returnedValue.executionTime = Double.valueOf(duration)/1000;
        System.out.println(ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue));
        return ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue);
    }
    @GetMapping(path = "/get-client/{clientId}")
    public ResponseEntity<ApiResponse> getAllClientbyId(@PathVariable String clientId)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse returnedValue = clientService.getClientByUsername(clientId);
        returnedValue.requestedCommand = "/clients";
        long duration = System.currentTimeMillis() - startTime;
        returnedValue.executionTime = Double.valueOf(duration)/1000;
        System.out.println(ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue));
        return ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue);
    }
}
