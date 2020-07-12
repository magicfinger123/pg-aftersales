package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.ClientDtoReq;
import com.pgagtersales.pgaftersales.service.ClientService;
import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
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
        returnedValue.executionTime = (double) duration /1000;
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
        returnedValue.executionTime = (double) duration /1000;
        System.out.println(ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue));
        return ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue);
    }
    @GetMapping(path = "/get-client/{clientId}")
    public ResponseEntity<ApiResponse> getAllClientbyId(@PathVariable int clientId)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse returnedValue = clientService.getClientById(clientId);
        returnedValue.requestedCommand = "/get-client/"+clientId;
        long duration = System.currentTimeMillis() - startTime;
        returnedValue.executionTime = (double) duration /1000;
        System.out.println(ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue));
        return ResponseEntity.status(returnedValue.getStatusCode()).body(returnedValue);
    }

    @PostMapping(path = "/client_action")
    public ResponseEntity<ApiResponse> addClient(@RequestBody ClientDtoReq clientDto)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = clientService.addClient(clientDto);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PutMapping(path = "/client_action/{id}")
    public ResponseEntity<ApiResponse> updateClient(@PathVariable int id, @RequestBody ClientDtoReq clientDto)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = clientService.updateClient(id, clientDto);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @DeleteMapping(path = "/client_action/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable int id)
    {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = clientService.deleteClient(id);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
