package com.pgagtersales.pgaftersales.shared;

import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.EmailRecipentEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.EmailsRepository;
import com.pgagtersales.pgaftersales.shared.dto.EmailsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {
    private Random RANDOM = new SecureRandom();
    private   final String ALPHABETS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private   final int ITERATIONS = 1000;
    private   final int KEY_LENGTH = 256;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    EmailsRepository emailsRepository;

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
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message(s).build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }
    public String getDate(){
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;*/
        LocalDateTime dateStart = LocalDateTime.now().plusHours(1);
        String d2 = dateStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return d2;
    }
    public String getTime(){
      /*  SimpleDateFormat sdhf = new SimpleDateFormat("HH:mm");
        String time = sdhf.format(new Date());
        return time;*/
        LocalDateTime dateStart = LocalDateTime.now().plusHours(1);
        String d2 = dateStart.format(DateTimeFormatter.ofPattern("HH:mm"));
        return d2;
    }
    public EmailsDto getEmails(String type){
        EmailRecipentEntity emailRecipentEntity = emailsRepository.findByType(type);
        EmailsDto emailsDto = new EmailsDto();
        if (emailRecipentEntity == null){
            emailsDto.setType("");
            emailsDto.setEmailAddress("powergencustomercare@gmail.com, powergenltd@gmail.com");
          //  emailsDto.setEmailAddress("ossaimike8@gmail.com");
        }else {
            BeanUtils.copyProperties(emailRecipentEntity, emailsDto);
        }
        return emailsDto;
    }
}
