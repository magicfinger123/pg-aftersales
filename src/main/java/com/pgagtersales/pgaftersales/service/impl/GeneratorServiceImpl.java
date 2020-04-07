package com.pgagtersales.pgaftersales.service.impl;


import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.GeneratorRepository;
import com.pgagtersales.pgaftersales.service.GeneratorService;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    GeneratorRepository generatorRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Override
    public List<GeneratorDto> searchGenerators(String alias, int page, int size) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<GeneratorEntity> generatorEntity = generatorRepository.searchGenerator(alias,pageable);
        if (generatorEntity == null)throw new UserServiceException("No result","searchGenerators returned null value");
        List<GeneratorEntity> genEntity = generatorEntity.getContent();
        for (GeneratorEntity gen:genEntity)
        {
            GeneratorDto genDto = new GeneratorDto();
            BeanUtils.copyProperties(gen,genDto);
            returnValue.add(genDto);
        }
        return returnValue;
    }


    @Override
    public List<GeneratorDto> getGenerators(int page, int size) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<GeneratorEntity> generatorPage = generatorRepository.findAll(pageable);
        if (generatorPage == null)throw new UserServiceException("No result","getGenerators returned null value");
        List<GeneratorEntity> generators = generatorPage.getContent();
        for (GeneratorEntity gen:generators) {
            GeneratorDto genDto = new GeneratorDto();
            BeanUtils.copyProperties(gen,genDto);
            returnValue.add(genDto);
        }
        return returnValue;
    }

    @Override
    public ApiResponse getGenByClientId(String clientId) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(0,5);
        Page<GeneratorEntity> generatorPageEntity = generatorRepository.findByClientId(clientId, pageable);
        if (generatorPageEntity==null||generatorPageEntity.isEmpty()) {
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Error Occured")
                    .developerMessage("ClientId not found")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else{
            List<GeneratorEntity> generators = generatorPageEntity.getContent();
            for (GeneratorEntity gen:generators) {
                GeneratorDto generatorDto = new GeneratorDto();
                BeanUtils.copyProperties(gen,generatorDto);
                returnValue.add(generatorDto);
            }
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse getGenerator(int id) throws UserServiceException{
        GeneratorDto returnValue = new GeneratorDto();
        GeneratorEntity generatorEntity = generatorRepository.findById(id);
        if (generatorEntity == null) {
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Error Occured")
                    .developerMessage("Id not found")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            BeanUtils.copyProperties(generatorEntity, returnValue);
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }
}
