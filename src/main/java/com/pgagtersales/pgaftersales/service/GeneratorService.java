package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;

import java.util.List;

public interface GeneratorService {
    List<GeneratorDto> searchGenerators(String alias, int page, int size);
    List<GeneratorDto> getGenerators(int page, int size);
    ApiResponse getGenByClientId(String clientId);
    ApiResponse getGenerator(int id);
}
