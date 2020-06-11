/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.response;

import com.pgagtersales.pgaftersales.io.HttpResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    @Autowired
    ApiResponse apiResponse;

    public ApiResponse successfullResponse() {
        apiResponse.statusCode = HttpResponses.HTTP_STATUS_OK;
        apiResponse.requestSuccessful = true;
        apiResponse.apiErrors = new ApiErrors();
        apiResponse.apiWarnings = new ApiWarnings();
        return apiResponse;
    }

    public ApiResponse failedResponse(int httpStatus) {
        apiResponse.statusCode = httpStatus;
        apiResponse.requestSuccessful = false;
        return apiResponse;
    }
}
