package com.pgagtersales.pgaftersales.exceptions;

import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = UserServiceException.class)
    public ResponseEntity<Object> handlesUserServiceException(UserServiceException ex, WebRequest req)
    {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .userMessage(ex.getLocalizedMessage())
                .developerMessage(ex.getDeveloperMessage())
                .errorCode(419)
                .StatusCode(400)
                .build();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
