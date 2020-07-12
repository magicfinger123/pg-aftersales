/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.restController;


import com.pgagtersales.pgaftersales.io.LogTimeFilter;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.service.PaymentService;
import com.pgagtersales.pgaftersales.service.ReportLogService;
import com.pgagtersales.pgaftersales.shared.dto.PaymentAdviseDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportSubmissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report_log")
public class ReportLogController {
    @Autowired
    private
    ReportLogService reportLogService;
    @Autowired
    private
    ApiResponse apiResponse;
    @Autowired
    private LogTimeFilter logTimeFilter;

    @PostMapping()
    public ResponseEntity<ApiResponse> updatePriceList(@RequestBody ReportLogDto reportLogDto)
    {
        ApiResponse apiResponse = reportLogService.addLog(reportLogDto);//.updatePriceList(slaPriceListDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/logbyuserbydate/{userid}/{date}")
    public ResponseEntity<ApiResponse> getByUserByDate(@PathVariable String userid,@PathVariable String date, @RequestParam(value = "page",defaultValue = "0")int page,
                                                       @RequestParam(value = "size",defaultValue = "25")int size)
    {
        ApiResponse apiResponse = reportLogService.getUserReportByDate(userid,date,page,size);//.updatePriceList(slaPriceListDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse> submitReport(@RequestBody ReportSubmissionDto dto)
    {
        ApiResponse apiResponse = reportLogService.submitReport(dto);//.updatePriceList(slaPriceListDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}