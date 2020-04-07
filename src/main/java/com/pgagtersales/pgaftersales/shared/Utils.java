package com.pgagtersales.pgaftersales.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    private Random RANDOM = new SecureRandom();
    private   final String ALPHABETS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private   final int ITERATIONS = 1000;
    private   final int KEY_LENGTH = 256;

    public String generateUserId(int length){
        return generateRandomString(length);
    }
    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i <= length; i++){
            returnValue.append(ALPHABETS.charAt(RANDOM.nextInt(ALPHABETS.length())));
        }
        return new String(returnValue);
    }
}
