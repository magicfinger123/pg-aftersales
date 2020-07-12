/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.EmailRecipentEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.EmailsRepository;
import com.pgagtersales.pgaftersales.service.EmailRecipentService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.EmailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailRecipentServiceImpl implements EmailRecipentService {
    @Autowired
    private EmailsRepository emailsRepository;

    @Autowired
    private ResponseBuilder responseBuilder;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private Utils utils;
    @Override
    public ApiResponse addEmailRecipent(EmailsDto emailsDto) {
        EmailRecipentEntity emailRecipentEntity = emailsRepository.findByType(emailsDto.getType());
        if (emailRecipentEntity != null) {
            throw new UserServiceException("Email category exist use modify instead", "Email already exist");
        }
        ModelMapper modelMapper = new ModelMapper();
        EmailRecipentEntity email = modelMapper.map(emailsDto, EmailRecipentEntity.class);
        EmailRecipentEntity saveUser = emailsRepository.save(email);
        if (saveUser == null){
            throw new UserServiceException("could not save email something went wrong","something went wrong");
        }
        ApiResponse apiResponse = utils.sucessApiResponse("email recipient  has been saved");
        return apiResponse;
    }

    @Override
    public ApiResponse getEMailRecipent(String type) {
        EmailRecipentEntity emailRecipentEntity = emailsRepository.findByType(type);
        if (emailRecipentEntity == null) {
            throw new UserServiceException("No email found for "+type, "No email found for "+type);
        }
        EmailsDto emailsDto = new EmailsDto();
        BeanUtils.copyProperties(emailRecipentEntity,emailsDto);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(emailsDto);
        return apiResponse;
    }

    @Override
    public ApiResponse upDateEmailRecipents(String id, EmailsDto emailsDto) {
        EmailRecipentEntity emailRecipentEntity = emailsRepository.findById(Integer.parseInt(id));//findById(Integer.valueOf(id));
        if (emailRecipentEntity == null) {
            throw new UserServiceException("Email does not exist", "Email does not exist");
        }
        ModelMapper modelMapper = new ModelMapper();
        EmailRecipentEntity email = modelMapper.map(emailsDto, EmailRecipentEntity.class);
        email.setId(emailRecipentEntity.getId());
        EmailRecipentEntity saveUser = emailsRepository.save(email);
        if (saveUser == null){
            throw new UserServiceException("could not save email something went wrong","something went wrong");
        }
        ApiResponse apiResponse = utils.sucessApiResponse("email recipient  has been updated");
        return apiResponse;
    }
}
