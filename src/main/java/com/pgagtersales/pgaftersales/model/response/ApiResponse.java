/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.response;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
@Data
public class ApiResponse {
        public Integer statusCode;
        public Boolean requestSuccessful;
        public Double executionTime;
        public ApiErrors apiErrors;
        public ApiWarnings apiWarnings;
        public String requestedCommand;
        public ResponseEntity<Object> responseEntity;
    }