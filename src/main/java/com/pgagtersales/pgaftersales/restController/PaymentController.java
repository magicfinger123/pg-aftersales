/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;

import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.OutstandingService;
import com.pgagtersales.pgaftersales.service.PaymentService;
import com.pgagtersales.pgaftersales.shared.dto.PaymentAdviseDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private
    PaymentService paymentService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private
    LogTimeFilter logTimeFilter;
    @PostMapping("/general")
    public ResponseEntity<ApiResponse> makePayment(@RequestBody PaymentAdviseDto paymentAdviseDto)
    {
        ApiResponse apiResponse = paymentService.makePayment(paymentAdviseDto);//.updatePriceList(slaPriceListDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/general/{id}")
    public ResponseEntity<ApiResponse> getPayments(@PathVariable String id)
    {
        ApiResponse apiResponse = paymentService.getPaymentsByClients(id);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
