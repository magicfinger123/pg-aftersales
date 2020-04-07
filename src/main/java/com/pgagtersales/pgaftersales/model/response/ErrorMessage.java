package com.pgagtersales.pgaftersales.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {
    private int StatusCode;
    private int errorCode;
    private String developerMessage;
    private String userMessage;
}
