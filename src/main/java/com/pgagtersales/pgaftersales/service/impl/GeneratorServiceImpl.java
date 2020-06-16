package com.pgagtersales.pgaftersales.service.impl;


import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.HttpResponses;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ErrorMessage;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.GeneratorRepository;
import com.pgagtersales.pgaftersales.service.GeneratorService;
import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorByClientIdDto;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    GeneratorRepository generatorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private SendMail sendMail;

    @Autowired
    NotificationMessages message;

    String[] recipent = {"exc.easy@gmail.com","ossaimike8@gmail.com"};

    @Override
    public ApiResponse searchGenerators(String alias, int page, int size) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<GeneratorEntity> generatorEntity = generatorRepository.searchGenerator(alias, pageable);
        if (generatorEntity == null) {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .userMessage("No result")
                    .developerMessage("getGenerators returned null value")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            List<Object>  errm = new ArrayList<>();
            errm.add(errorMessage);
            apiResponse.apiErrors.apiErrorList = errm;
            apiResponse.apiErrors.errorCount = errm.size();
            return apiResponse;
        } else {
            List<GeneratorEntity> genEntity = generatorEntity.getContent();
            for (GeneratorEntity gen : genEntity) {
                GeneratorDto genDto = new GeneratorDto();
                BeanUtils.copyProperties(gen, genDto);
                returnValue.add(genDto);
            }
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }


    @Override
    public ApiResponse getGenerators(int page, int size) {
        List<GeneratorDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<GeneratorEntity> generatorPage = generatorRepository.findAll(pageable);
        if (generatorPage == null || generatorPage.isEmpty()) {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .userMessage("No result")
                    .developerMessage("getGenerators returned null value")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            List<GeneratorEntity> generators = generatorPage.getContent();
            for (GeneratorEntity gen : generators) {
                GeneratorDto genDto = new GeneratorDto();
                BeanUtils.copyProperties(gen, genDto);

                returnValue.add(genDto);

            }
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse getGenByClientId(String clientId) {
        ClientsEntity clientsEntity = clientRepository.findById(Integer.parseInt(clientId));
        if (clientsEntity == null){
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Error Occured")
                    .developerMessage("ClientId not found")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        }
        ModelMapper modelMapper = new ModelMapper();
        ClientDto clientDto = modelMapper.map(clientsEntity, ClientDto.class);
        List<GeneratorDto> returnValue = new ArrayList<>();
        GeneratorByClientIdDto generatorByClientIdDto = new GeneratorByClientIdDto();
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
            generators.forEach(gen -> {
                GeneratorDto generatorDto = new GeneratorDto();
                BeanUtils.copyProperties(gen,generatorDto);
                returnValue.add(generatorDto);});
        }
        generatorByClientIdDto.setClientDto(clientDto);
        generatorByClientIdDto.setGeneratorDtos(returnValue);
        ApiResponse apiResponse = responseBuilder.successfullResponse();
        apiResponse.responseEntity = ResponseEntity.ok(generatorByClientIdDto);
        return apiResponse;
    }
    @Override
    public ApiResponse getGenerator(int id) throws UserServiceException{
        GeneratorDto returnValue = new GeneratorDto();
        GeneratorEntity generatorEntity = generatorRepository.findById(id);
        if (generatorEntity == null) {
            throw new UserServiceException("something went wrong","generator id not found");
        } else {
            BeanUtils.copyProperties(generatorEntity, returnValue);
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }
    @SneakyThrows
    @Override
    public ApiResponse addGenerator(GeneratorDto generatorDto, Boolean sendNotification) {
        GeneratorDto returnValue = new GeneratorDto();
        GeneratorEntity generatorEntity = generatorRepository.findByEngineSerial(generatorDto.getEngineSerial());
        if (generatorEntity != null) {
            throw new UserServiceException("user already exist","user already exist");
        }
        //GeneratorEntity genEntity = new GeneratorEntity();
        //BeanUtils.copyProperties(generatorDto, genEntity);
        ModelMapper modelMapper = new ModelMapper();
        GeneratorEntity genEntity =  modelMapper.map(generatorDto, GeneratorEntity.class);
        GeneratorEntity saveUser = generatorRepository.save(genEntity);
        BeanUtils.copyProperties(saveUser, returnValue);
        ApiResponse apiResponse = responseBuilder.successfullResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        if (sendNotification){
            sendMail.sendEmailWithAttachment(message.addGenNotification(generatorDto),recipent,recipent, "GENERATOR SERVICE NOTIFICATION");
        }
        return apiResponse;
    }
    @Override
    public ApiResponse updateGenerator(int id, GeneratorDto generatorDto) {
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
            BeanUtils.copyProperties(generatorDto, generatorEntity);
            GeneratorEntity saveUser = generatorRepository.save(generatorEntity);
            BeanUtils.copyProperties(saveUser, returnValue);
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }
    @Override
    public ApiResponse deleteGenerator(int id) {
        GeneratorEntity generatorEntity = generatorRepository.findById(id);
        if (generatorEntity == null) {
            ErrorMessage errorMessage= ErrorMessage.builder()
                    .userMessage("Unable to delete generator")
                    .developerMessage("Generator not in database")
                    .build();
            ApiResponse apiResponse = responseBuilder.failedResponse(HttpResponses.HTTP_STATUS_BAD_REQUEST);
            apiResponse.responseEntity = ResponseEntity.badRequest().body(errorMessage);
            return apiResponse;
        } else {
            generatorRepository.delete(generatorEntity);
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok("Successfully deleted generator with "+generatorEntity.getAlternator_serial());
            return apiResponse;
        }
    }
}
