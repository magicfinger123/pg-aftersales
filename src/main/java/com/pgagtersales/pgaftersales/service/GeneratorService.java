package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;

import java.util.List;

public interface GeneratorService {
    ApiResponse searchGenerators(String alias, int page, int size);
    ApiResponse getGenerators(int page, int size);
    ApiResponse getGenByClientId(String clientId);
    ApiResponse getGenByClientDto(String clientId);
    ApiResponse getGenerator(int id);
    ApiResponse addGenerator(GeneratorDto generatorDto, Boolean sendNotification);
    ApiResponse updateGenerator(int id, GeneratorDto generatorDto);
    ApiResponse deleteGenerator(int id);
}
