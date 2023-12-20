package com.pgagtersales.pgaftersales.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.entity.InventoryItemEntity;
import com.pgagtersales.pgaftersales.io.entity.StoreInventoryEntity;
import com.pgagtersales.pgaftersales.io.entity.StoreInventoryItemEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.InventoryCreationDto;
import com.pgagtersales.pgaftersales.repository.*;
import com.pgagtersales.pgaftersales.service.StoreInventoryService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class StoreInventoryServiceImpl implements StoreInventoryService {

    @Autowired
    StoreInventoryRepository inventoryRepository;

    @Autowired
    StoreInventoryItemRepository inventoryItemRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private Utils utils;

    @Autowired
    SendMail sendMail;

    @Autowired
    NotificationMessages message;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();

    @Override
    public ApiResponse createInventory(StoreInventoryDto inventoryDto) {
        String ccRecipent = utils.getEmails("default").getEmailAddress();
        if (inventoryRepository.findByInventoryName(inventoryDto.getInventoryName())!=null) {
            throw new UserServiceException("Inventory name already exist, use a different name","inventory name already exist");
        }
        StoreInventoryDto dto = new StoreInventoryDto();
        BeanUtils.copyProperties(inventoryDto,dto);
        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        dto.setInventoryId(utils.generateId(20));
        StoreInventoryEntity entity = new StoreInventoryEntity();
        BeanUtils.copyProperties(dto,entity);
        System.out.println("inventory Entity: "+entity);
        entity.setUpdatedBy(message.getUserDetails().getUserId());
        StoreInventoryEntity createInventory = inventoryRepository.save(entity);
        InventoryCreationDto inventoryCreationDto = new InventoryCreationDto();
        BeanUtils.copyProperties(inventoryDto,inventoryCreationDto);
        try {
            sendMail.sendEmailWithAttachment(message.newInventory(inventoryCreationDto), ccRecipent, AppConstants.AFTERSALES_RECIPENTS, "New Inventory Manager Created");
        } catch (Exception ignored){

        }
        ApiResponse apiResponse = utils.sucessApiResponse("Inventory created successfully");
        try{
            notificationRequestDto.setTarget("admin");
            notificationRequestDto.setTitle("PEL");
            notificationRequestDto.setBody("New Inventory Created by "+message.getUserDetails().getFirst_name()+" for "+inventoryDto.getInventoryManager());
            notificationController.sendPnsToTopic(notificationRequestDto);
        } catch (Exception ignored) {

        }
        return apiResponse;
    }

    @Override
    public ApiResponse getAllInventory(int page, int size) {
        List<StoreInventoryDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<StoreInventoryEntity> inventoryEntityPage = inventoryRepository.findAll(pageable);
            List<StoreInventoryEntity> inventoryEntities = inventoryEntityPage.getContent();
            for (StoreInventoryEntity inventoryEntity : inventoryEntities) {
                StoreInventoryDto inventoryDto = new StoreInventoryDto();
                BeanUtils.copyProperties(inventoryEntity, inventoryDto);
                returnedValue.add(inventoryDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse updateInventory(StoreInventoryDto inventoryDto) {
        if (inventoryRepository.findByInventoryName(inventoryDto.getInventoryId())==null) {
            throw new UserServiceException("Inventory does not exist","Inventory does not exist");
        }
        StoreInventoryEntity entity = new StoreInventoryEntity();
        BeanUtils.copyProperties(inventoryDto,entity);
        System.out.println("inventory Entity: "+entity);
        try {
            StoreInventoryEntity createInventory = inventoryRepository.save(entity);
            ApiResponse apiResponse = utils.sucessApiResponse("Inventory updated successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to update Inventory","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse addItemToInventory(StoreInventoryItemDto inventoryItemDto) {
        String ccRecipent = utils.getEmails("default").getEmailAddress();
        StoreInventoryEntity inv = inventoryRepository.findByInventoryId(inventoryItemDto.getInventoryId());
        if (inv==null) {
            throw new UserServiceException("No inventory selected","No inventory selected");
        }
        System.out.println("base64 string: " + inventoryItemDto.getImageBase64());
        inventoryItemDto.setInventoryManager(inv.getInventoryManager());
        inventoryItemDto.setInventoryItemId(utils.generateId(20));
        StoreInventoryItemEntity entity = new StoreInventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDto,entity);
        System.out.println("inventory Entity: "+entity);
//        ObjectMapper mapper = new ObjectMapper();
//        byte[] encoded = mapper.convertValue(inventoryItemDto.getImageBase64(), byte[].class);
//        byte[] name = Base64.getEncoder().encode(inventoryItemDto.getImageBase64().getBytes());
//        entity.setImageBase64(name);
        try {
            StoreInventoryItemEntity createInventoryItem = inventoryItemRepository.save(entity);
            sendMail.sendEmailWithAttachment(message.addStoreInventoryItem(inventoryItemDto), ccRecipent, AppConstants.AFTERSALES_RECIPENTS, "Item Added to Inventory mannaged by "+inv.getInventoryManager());
            ApiResponse apiResponse = utils.sucessApiResponse("Inventory item created successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create Inventory item","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse upDateInventoryItem(StoreInventoryItemDto inventoryItemDto) {
        if (inventoryRepository.findByInventoryId(inventoryItemDto.getInventoryId())==null) {
            throw new UserServiceException("No inventory Selected","Invalid inventory Id");
        }
        if (inventoryItemRepository.findByInventoryItemId(inventoryItemDto.getInventoryItemId())==null) {
            throw new UserServiceException("Item does not exist","Invalid inventory item Id");
        }
        StoreInventoryItemEntity entity = inventoryItemRepository.findByInventoryItemId(inventoryItemDto.getInventoryItemId());
        //InventoryItemEntity entity = new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDto,entity);
        System.out.println("inventory Entity: "+entity);
        try {
            StoreInventoryItemEntity createInventoryItem = inventoryItemRepository.save(entity);
            ApiResponse apiResponse = utils.sucessApiResponse(inventoryItemDto.getItemName()+"  has been updated");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable update item","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse deleteInventoryItem(String inventoryItemId) {
        StoreInventoryItemEntity getItemEntity = inventoryItemRepository.findByInventoryItemId(inventoryItemId);
        if (getItemEntity==null) {
            throw new UserServiceException("item with id "+inventoryItemId+ " does not exist","item with id "+inventoryItemId+ " does not exist");
        }
        System.out.println("itemEntity to delete: "+getItemEntity);
        try {
            inventoryItemRepository.delete(getItemEntity);
            ApiResponse apiResponse = utils.sucessApiResponse("item deleted successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to delete item","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getItemsByInventoryIds(UpdateInventoryItemDto inventoryItemDtos) {
        return null;
    }

    @Override
    public ApiResponse getItemsByInventoryId(String inventoryId) {
        ArrayApiResponse returnedValue = new ArrayApiResponse();
        List<StoreInventoryItemDto> inventories = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<StoreInventoryItemEntity> entityPage = inventoryItemRepository.findByInventoryId(inventoryId,pageable);
            List<StoreInventoryItemEntity> inventoryEntities = entityPage.getContent();
            for (StoreInventoryItemEntity inventoryEntity : inventoryEntities) {
              //  String decoded = mapper.convertValue(inventoryEntity.getImageBase64(), String.class);
                StoreInventoryItemDto inventoryDto = new StoreInventoryItemDto();
                BeanUtils.copyProperties(inventoryEntity, inventoryDto);
              //  inventoryDto.setImageBase64(decoded);
                inventories.add(inventoryDto);
            }
            returnedValue.setId(inventoryId);
            returnedValue.setItem(inventories);
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }
}
