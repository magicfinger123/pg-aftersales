package com.pgagtersales.pgaftersales.shared;

import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    private Random RANDOM = new SecureRandom();
    private   final String ALPHABETS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private   final int ITERATIONS = 1000;
    private   final int KEY_LENGTH = 256;

    @Autowired
    ResponseBuilder responseBuilder;

    public String generateId(int length){
        return generateRandomString(length);
    }
    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i <= length; i++){
            returnValue.append(ALPHABETS.charAt(RANDOM.nextInt(ALPHABETS.length())));
        }
        return new String(returnValue);
    }
    public ApiResponse sucessApiResponse(String s) {
        ApiResponse apiResponse = responseBuilder.successfullResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message(s).build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }
}
